package ir.ayantech.pishkhancore.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.CallingState
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.databinding.FragmentAyanHistoryBinding
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.pishkhancore.ui.adapter.AyanHistoryAdapter
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import ir.ayantech.whygoogle.fragment.WhyGoogleFragmentTransactionAnimation
import ir.ayantech.whygoogle.helper.*

open class AyanHistoryFragment : WhyGoogleFragment<FragmentAyanHistoryBinding>() {

    var dataset = arrayListOf<Any>()
    var onDetailsClicked: ((PaymentHistoryGetTransactionInfoOutput) -> Unit)? = null
    var changeStatus: OnChangeStatus? = null
    var failure: OnFailure? = null
    var onGetHistoryResult: SimpleCallBack? = null

    open var productName: String = ""

    override fun onCreate() {
        super.onCreate()

        accessViews {
            swipeRefreshLayout.setColorSchemeResources(R.color.AyanHistoryFragmentSwipeRefreshColor)
            inquiryHistoryWp10?.showProgressBar()
            historyRv.verticalSetup()
            delayed {
                getHistory()
            }
            requireContext().let { context ->
                swipeRefreshLayout.setColorSchemeColors(
                    ContextCompat.getColor(context, R.color.AyanHistoryFragmentSwipeRefreshSchemeColor1),
                    ContextCompat.getColor(context, R.color.AyanHistoryFragmentSwipeRefreshSchemeColor2)
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
                                historyRv.adapter =
                                    AyanHistoryAdapter(dataset) { item, viewId, position ->
                                        item?.let { historyItem ->
                                            (historyItem as? Transaction)?.let { transaction ->
                                                PishkhanCore.ayanApi?.ayanCall<PaymentHistoryGetTransactionInfoOutput>(
                                                    AyanCallStatus {
                                                        success {
                                                            it.response?.Parameters?.let {
                                                                onDetailsClicked?.invoke(it)
                                                            }
                                                        }
                                                        changeStatus?.let { onChangeStatus ->
                                                            changeStatus(onChangeStatus)
                                                        }
                                                        failure?.let { onFailure ->
                                                            failure(onFailure)
                                                        }
                                                    },
                                                    EndPoint.PaymentHistoryGetTransactionInfo,
                                                    PaymentHistoryGetTransactionInfoInput(
                                                        transaction.UniqueID
                                                    ),
                                                )
                                            }
                                        }
                                    }

                                onGetHistoryResult?.invoke()

//                                if (!dataset.isNullOrEmpty() && showAds) {
//                                    adView = AdvertisementCore.requestNativeAds(
//                                        requireContext(),
//                                        R.layout.ayan_native_ad
//                                    ) {
//                                        adView?.let { dataset.add(1, it) }
//                                        (historyRv.adapter as AyanHistoryAdapter).updateItems(
//                                            dataset
//                                        )
//                                    }
//                                }
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
                    failure?.let { onFailure ->
                        failure(onFailure)
                    }
                },
                EndPoint.PaymentHistoryGetTransactionList,
                PaymentHistoryGetTransactionListInput(
                    TransactionCategoryTypeName = "",
                    TransactionTypeName = productName
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
            R.anim.h_fragment_exit,
            R.anim.h_fragment_pop_enter,
            R.anim.h_fragment_pop_exit
        )
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAyanHistoryBinding
        get() = FragmentAyanHistoryBinding::inflate
}