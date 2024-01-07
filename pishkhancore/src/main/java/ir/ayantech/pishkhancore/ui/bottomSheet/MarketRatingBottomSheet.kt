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
import ir.ayantech.whygoogle.helper.nullableFragmentArgument
import java.io.Serializable

class MarketRatingBottomSheet(
//    private val activity: WhyGoogleActivity<*>,
//    private val applicationId: String,
//    private val marketName: String,
//    private val onOptionsClicked: ((hasRated: Boolean) -> Unit)? = null
) : AyanBaseBottomSheet<BottomSheetRatingBinding>() {

    @Deprecated(message = "pass the market name parameter to handle which market's intent should be call.")
    constructor(
        activity: WhyGoogleActivity<*>,
        applicationId: String,
        onOptionsClicked: ((hasRated: Boolean) -> Unit)? = null
    ) : this() {
        this.applicationId = applicationId
        this.onOptionsClicked = onOptionsClicked as Serializable
    }

    @Deprecated(message = "passing parameters in constructor is Deprecated. create an instance of class and pass parameters with one of kotlin scope functions like also scope function.", level = DeprecationLevel.ERROR)
    constructor(
        activity: WhyGoogleActivity<*>,
        applicationId: String,
        marketName: String,
        onOptionsClicked: ((hasRated: Boolean) -> Unit)? = null
    ) : this() {
        this.applicationId = applicationId
        this.marketName = marketName
        this.onOptionsClicked = onOptionsClicked as Serializable
    }

    var applicationId: String? by nullableFragmentArgument(null)
    var marketName: String? by nullableFragmentArgument(null)
    var onOptionsClicked: Serializable? by nullableFragmentArgument(null)

    override val binder: (LayoutInflater) -> BottomSheetRatingBinding
        get() = BottomSheetRatingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            yesBtn.setOnClickListener {
                (requireActivity() as WhyGoogleActivity<*>).showRatingIntent(
                    applicationId = applicationId ?: "",
                    marketName = marketName ?: ""
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
        MarketRating.saveUserHasRated(requireActivity(), hasRated = hasRated)
        (onOptionsClicked as? ((hasRated: Boolean) -> Unit))?.invoke(hasRated)
        dismiss()
    }
}