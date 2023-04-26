package ir.ayantech.pishkhancore.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.databinding.BottomSheetRatingBinding
import ir.ayantech.pishkhancore.helper.showRatingIntent
import ir.ayantech.pishkhancore.storage.MarketRating
import ir.ayantech.whygoogle.activity.WhyGoogleActivity

class MarketRatingBottomSheet(
    private val activity: WhyGoogleActivity<*>,
    private val applicationId: String,
    private val marketName: String,
    private val onOptionsClicked: ((hasRated: Boolean) -> Unit)? = null
) : AyanBaseBottomSheet<BottomSheetRatingBinding>() {

    @Deprecated(message = "pass the market name parameter to handle which market's intent should be call.")
    constructor(
        activity: WhyGoogleActivity<*>,
        applicationId: String,
        onOptionsClicked: ((hasRated: Boolean) -> Unit)? = null
    ) : this(activity, applicationId,"", onOptionsClicked)

    override val binder: (LayoutInflater) -> BottomSheetRatingBinding
        get() = BottomSheetRatingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            yesBtn.setOnClickListener {
                activity.showRatingIntent(
                    applicationId = applicationId,
                    marketName = marketName
                ) {
                    Toast.makeText(context, getString(R.string.thanks_for_your_feedback), Toast.LENGTH_SHORT).show()
                }
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
}