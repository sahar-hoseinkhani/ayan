package ir.ayantech.pishkhancore.helper

import android.content.Intent
import android.net.Uri
import android.util.Log
import ir.ayantech.pishkhancore.storage.MarketRating
import ir.ayantech.pishkhancore.ui.bottomSheet.MarketRatingBottomSheet
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.trying

fun WhyGoogleActivity<*>.showRatingBottomSheet(applicationId: String, callback: ((hasRated: Boolean) -> Unit)? = null) {
    if (!MarketRating.getUserHasRated(this)) {
        trying {
            MarketRatingBottomSheet(
                activity = this,
                applicationId = applicationId,
                onOptionsClicked = callback
            ).show(this.supportFragmentManager, MarketRatingBottomSheet::class.java.simpleName)
        }
    } else {
        callback?.invoke(true)
    }
}

fun WhyGoogleActivity<*>.showCafeBazaarIntent(
    applicationId: String,
    onFailed: (() -> Unit)? = null
) {
    try {
        val intent = Intent(Intent.ACTION_EDIT)
        intent.data = Uri.parse("bazaar://details?id=$applicationId")
        intent.setPackage("com.farsitel.bazaar")
        startActivity(intent)
    } catch (e: Exception) {
        Log.e("MarketIntent", "startMarketIntent: Cafebazaar => ${e.printStackTrace()}")
        onFailed?.invoke()
    }
}

fun WhyGoogleActivity<*>.showMyketIntent(
    applicationId: String,
    onFailed: (() -> Unit)? = null
) {
    try {
        val url = "myket://comment?id=$applicationId"
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)
        startActivity(intent)
    } catch (e: Exception) {
        Log.e("MarketIntent", "startMarketIntent: Myket => ${e.printStackTrace()}")
        onFailed?.invoke()
    }
}