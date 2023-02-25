package ir.ayantech.pishkhancore.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import ir.ayantech.pishkhancore.databinding.BottomSheetRatingBinding
import ir.ayantech.pishkhancore.helper.showCafeBazaarIntent
import ir.ayantech.pishkhancore.helper.showMyketIntent
import ir.ayantech.pishkhancore.storage.MarketRating
import ir.ayantech.whygoogle.activity.WhyGoogleActivity

class MarketRatingBottomSheet(
    private val activity: WhyGoogleActivity<*>,
    private val applicationId: String
) : AyanBaseBottomSheet<BottomSheetRatingBinding>() {

    override val binder: (LayoutInflater) -> BottomSheetRatingBinding
        get() = BottomSheetRatingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            yesBtn.setOnClickListener {
                showRatingIntent()
                saveUserHasRated(hasRated = true)
                dismiss()
            }
            laterBtn.setOnClickListener {
                saveUserHasRated(hasRated = false)
                dismiss()
            }
        }
    }

    private fun saveUserHasRated(hasRated: Boolean) {
        MarketRating.saveUserHasRated(activity, hasRated = hasRated)
    }

    private fun showRatingIntent() {
        activity.showCafeBazaarIntent(
            applicationId
        ) {
            activity.showMyketIntent(applicationId)
        }
    }
}