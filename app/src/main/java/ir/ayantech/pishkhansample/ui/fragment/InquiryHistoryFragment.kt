package ir.ayantech.pishkhansample.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.ui.adapter.AyanInquiryHistoryAdapter
import ir.ayantech.pishkhansample.databinding.FragmentInquiryHistoryBinding
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import ir.ayantech.whygoogle.helper.verticalSetup

class InquiryHistoryFragment : WhyGoogleFragment<FragmentInquiryHistoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentInquiryHistoryBinding
        get() = FragmentInquiryHistoryBinding::inflate

    override fun onCreate() {
        super.onCreate()

        getInquiryHistory()
        binding.inquiryHistoryRv.verticalSetup()
    }

    private fun getInquiryHistory() {
        accessViews {
            PishkhanCore.getInquiryHistory(
                "InquiryElectricBill",
                changeStatus = { },
                failure = { }
            )
            { response ->
                if (response != null) {
                    inquiryHistoryRv.adapter =
                        AyanInquiryHistoryAdapter(requireContext(), response, onChange = {
                            getInquiryHistory()
                        }) { item, viewId, _ ->
                            item?.let { it ->

                            }
                        }
                }
            }
        }
    }
}