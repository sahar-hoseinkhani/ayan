package ir.ayantech.pishkhancore.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.CallingState
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.databinding.FragmentAyanHistoryBinding
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.pishkhancore.ui.adapter.HistoryAdapter
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanErrorBottomSheet
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import ir.ayantech.whygoogle.helper.*

class AyanHistoryFragment : WhyGoogleFragment<FragmentAyanHistoryBinding>() {

    override fun onCreate() {
        super.onCreate()
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
        accessViews {
            PishkhanCore.ayanApi?.ayanCall<PaymentHistoryGetTransactionListOutput>(
                AyanCallStatus {
                    success {
                        it.response?.Parameters?.let {
                            it.let {
                                totalBillsCountTv.text = it.TotalNumberOfTransactions.toString()
                                totalAmountTv.text = it.TotalAmountOfTransactions.formatAmount("")
                                historyRv.adapter =
                                    HistoryAdapter(
                                        it.Transactions ?: listOf()
                                    ) { item, viewId, position ->
                                        PishkhanCore.ayanApi?.simpleCall<PaymentHistoryGetTransactionInfoOutput>(
                                            EndPoint.PaymentHistoryGetTransactionInfo,
                                            item?.UniqueID?.let { it1 ->
                                                PaymentHistoryGetTransactionInfoInput(it1)
                                            }
                                        ) { resp ->
                                            start(AyanHistoryDetailFragment().also {
                                                it.transaction = resp
                                            })
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

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAyanHistoryBinding
        get() = FragmentAyanHistoryBinding::inflate
}