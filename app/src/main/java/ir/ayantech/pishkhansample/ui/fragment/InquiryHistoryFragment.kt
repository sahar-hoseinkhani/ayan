package ir.ayantech.pishkhansample.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.ui.adapter.AyanInquiryHistoryAdapter
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanCheckStatusBottomSheet
import ir.ayantech.pishkhansample.databinding.FragmentInquiryHistoryBinding
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import ir.ayantech.whygoogle.helper.verticalSetup
import java.io.Serializable

class InquiryHistoryFragment : WhyGoogleFragment<FragmentInquiryHistoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentInquiryHistoryBinding
        get() = FragmentInquiryHistoryBinding::inflate

    override fun onCreate() {
        super.onCreate()

        getInquiryHistory()
        binding.inquiryHistoryRv.verticalSetup()

        binding.tempBtn.setOnClickListener {
            AyanCheckStatusBottomSheet()
                .also {
                    it.applicationUniqueToken = ""
                    it.additionalData = ""
                    it.mobileNumber = null
                    it.referenceToken = null
                    it.callBack = { b: Boolean ->
                            Log.d("flgbsdk", "onCreate: dlfkgbsldkf $b")
                    } as Serializable
                }
                .show(requireActivity().supportFragmentManager, "fdhgbksdjgfb")
        }
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