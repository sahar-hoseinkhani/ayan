package ir.ayantech.pishkhancore.ui.bottomSheet

import android.content.DialogInterface
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
    private val applicationId: String,
    private val onOptionsClicked: ((hasRated: Boolean) -> Unit)? = null
) : AyanBaseBottomSheet<BottomSheetRatingBinding>() {

    override val binder: (LayoutInflater) -> BottomSheetRatingBinding
        get() = BottomSheetRatingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            yesBtn.setOnClickListener {
                showRatingIntent()
                onButtonClicked(hasRated = true)
            }
            laterBtn.setOnClickListener {
                onButtonClicked(hasRated = false)
            }
        }
    }

    private fun onButtonClicked(hasRated: Boolean) {
        MarketRating.saveUserHasRated(activity, hasRated = hasRated)
        onOptionsClicked?.invoke(hasRated)
        dismiss()
    }

    private fun showRatingIntent() {
        activity.showCafeBazaarIntent(
            applicationId
        ) {
            activity.showMyketIntent(applicationId)
        }
    }
}