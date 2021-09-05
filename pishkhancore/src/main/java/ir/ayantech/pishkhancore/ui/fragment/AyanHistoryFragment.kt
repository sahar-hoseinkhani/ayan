package ir.ayantech.pishkhancore.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.adivery.sdk.AdiveryNativeAdView
import com.adivery.sdk.networks.adivery.AdiveryNativeAd
import ir.ayantech.advertisement.core.AdvertisementCore
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.CallingState
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.databinding.FragmentAyanHistoryBinding
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.pishkhancore.ui.adapter.AyanHistoryAdapter
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanErrorBottomSheet
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import ir.ayantech.whygoogle.fragment.WhyGoogleFragmentTransactionAnimation
import ir.ayantech.whygoogle.helper.*

open class AyanHistoryFragment : WhyGoogleFragment<FragmentAyanHistoryBinding>() {

    var dataset = arrayListOf<Any>()
    var showAds = false
    var onDetailsClicked: ((PaymentHistoryGetTransactionInfoOutput) -> Unit)? = null
    private var adView: AdiveryNativeAdView? = null

    override fun onCreate() {
        super.onCreate()

        PishkhanCore.getAppConfigAdvertisement {
            showAds = it.Active
            if (it.Active) {
                activity?.application?.let { application ->
                    AdvertisementCore.initialize(
                        application,
                        it.Sources.first { it.Key == "appKey" }.Value,
                        it.Sources.first { it.Key == "adiveryInterstitialAdUnitID" }.Value,
                        it.Sources.first { it.Key == "adiveryBannerAdUnitID" }.Value,
                        it.Sources.first { it.Key == "adiveryNativeAdUnitID" }.Value
                    )
//                    AdvertisementCore.initialize(
//                        application,
//                        "33999115-bdec-4ed8-b9ec-1e4ab872669e",
//                        "cc12c56b-64ce-42b1-8330-5e50c2df1882",
//                        "69a84172-20ad-4e61-b1bf-d8d8c631e113",
//                        "5e842580-818d-465f-a79a-77a3b13a6bb1"
//                    )
                }
            }
        }

        accessViews {
            inquiryHistoryWp10?.showProgressBar()
            historyRv.verticalSetup()
            delayed {
                getHistory()
            }
            requireContext().let { context ->
                swipeRefreshLayout.setColorSchemeColors(
                    ContextCompat.getColor(context, R.color.colorPrimary),
                    ContextCompat.getColor(context, R.color.colorAccent)
                )
            }
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false
                getHistory()
            }
        }
    }

    private fun getHistory() {
        dataset.clear()
        accessViews {
            PishkhanCore.ayanApi?.ayanCall<PaymentHistoryGetTransactionListOutput>(
                AyanCallStatus {
                    success {
                        it.response?.Parameters?.let {
                            it.let {
                                totalBillsCountTv.text = it.TotalNumberOfTransactions.toString()
                                totalAmountTv.text = it.TotalAmountOfTransactions.formatAmount("")
                                dataset.addAll(it.Transactions ?: listOf())
//                                if (!it.Transactions.isNullOrEmpty() && showAds) {
//                                    dataset.add(1, "")
//                                }
                                historyRv.adapter =
                                    AyanHistoryAdapter(dataset) { item, viewId, position ->
                                        item?.let { historyItem ->
                                            (historyItem as? Transaction)?.let { transaction ->
                                                PishkhanCore.ayanApi?.simpleCall<PaymentHistoryGetTransactionInfoOutput>(
                                                    EndPoint.PaymentHistoryGetTransactionInfo,
                                                    PaymentHistoryGetTransactionInfoInput(
                                                        transaction.UniqueID
                                                    )
                                                ) { resp ->
                                                    resp?.let { it1 -> onDetailsClicked?.invoke(it1) }
//                                                    start(AyanHistoryDetailFragment().also {
//                                                        it.transaction = resp
//                                                    })
                                                }
                                            }
                                        }
                                    }

                                if (!dataset.isNullOrEmpty() && showAds){
                                    adView = AdvertisementCore.requestNativeAds(
                                        requireContext(),
                                        R.layout.ayan_native_ad
                                    ) {
                                        adView?.let { dataset.add(1, it) }
                                        (historyRv.adapter as AyanHistoryAdapter).updateItems(dataset)
                                    }
                                }
                                handleNoItemTv()
                            }
                        }
                    }
                    changeStatus {
                        when (it) {
                            CallingState.NOT_USED -> {
                            }
                            CallingState.LOADING -> {
                                if (!inquiryHistoryWp10.isVisible()) {
                                    inquiryHistoryWp10.makeVisible()
                                    inquiryHistoryWp10.showProgressBar()
                                }
                            }
                            CallingState.FAILED -> {
                                inquiryHistoryWp10.makeGone()
                                inquiryHistoryWp10.hideProgressBar()
                            }
                            CallingState.SUCCESSFUL -> {
                                inquiryHistoryWp10.makeGone()
                                inquiryHistoryWp10.hideProgressBar()
                            }
                        }
                    }
                    failure {
                        AyanErrorBottomSheet(requireContext(), it.failureMessage) {
                            it.reCallApi()
                        }.show()
                    }
                },
                EndPoint.PaymentHistoryGetTransactionList,
                PaymentHistoryGetTransactionListInput(
                    "", ""
                )
            )
        }
    }

    private fun handleNoItemTv() {
        accessViews {
            if (historyRv.adapter?.itemCount == 0) {
                noItemTv.makeVisible()
            } else {
                noItemTv.makeGone()
            }
        }
    }

    override fun getFragmentTransactionAnimation(): WhyGoogleFragmentTransactionAnimation? {
        return WhyGoogleFragmentTransactionAnimation(
            R.anim.h_fragment_enter,
            R.anim.h_fragment_pop_exit,
            R.anim.h_fragment_pop_enter,
            R.anim.h_fragment_pop_exit
        )
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAyanHistoryBinding
        get() = FragmentAyanHistoryBinding::inflate
}