package ir.ayantech.pishkhancore.helper

import android.content.Intent
import android.net.Uri
import android.util.Log
import ir.ayantech.pishkhancore.storage.MarketRating
import ir.ayantech.pishkhancore.ui.bottomSheet.MarketRatingBottomSheet
import ir.ayantech.whygoogle.activity.WhyGoogleActivity

fun WhyGoogleActivity<*>.showRatingBottomSheet(applicationId: String) {
    if (!MarketRating.getUserHasRated(this)) {
        MarketRatingBottomSheet(
            activity = this,
            applicationId = applicationId
        ).show(this.supportFragmentManager, MarketRatingBottomSheet::class.java.simpleName)
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