package ir.ayantech.pishkhancore.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.databinding.FragmentRulesBinding
import ir.ayantech.pishkhancore.ui.adapter.AyanRulesAdapter
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import ir.ayantech.whygoogle.fragment.WhyGoogleFragmentTransactionAnimation
import ir.ayantech.whygoogle.helper.openPhoneWithNumber
import ir.ayantech.whygoogle.helper.verticalSetup

open class AyanRulesFragment : WhyGoogleFragment<FragmentRulesBinding>() {
    var changeStatus: OnChangeStatus? = null
    var failure: OnFailure? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRulesBinding
        get() = FragmentRulesBinding::inflate

    override fun onCreate() {
        super.onCreate()
        getRules()
    }

    private fun getRules() {
        accessViews {
            PishkhanCore.getAppConfigBasicInformation(
                changeStatus = changeStatus!!,
                failure = failure!!
            ) { response ->
                rulesRV.apply {
                    verticalSetup()
                    adapter = AyanRulesAdapter(termsAndCondition = response.TermsAndConditions)

                }
                contactSupportBtn.setOnClickListener {
                    response.Support.first { it.Key == "تماس با پشتیبانی" }.Value.openPhoneWithNumber(
                        requireContext()
                    )
                }
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
}