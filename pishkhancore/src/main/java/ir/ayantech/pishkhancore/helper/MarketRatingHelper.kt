package ir.ayantech.pishkhancore.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import ir.ayantech.pishkhancore.storage.MarketRating
import ir.ayantech.pishkhancore.ui.bottomSheet.MarketRatingBottomSheet
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.trying

private const val CAFE_BAZAAR = "cafebazaar"
private const val MYKET = "myket"
private const val PLAY_STORE = "playstore"
private const val XIAOMI_STORE = "xiaomistore"
private const val GALAXY_STORE = "galaxystore"

fun WhyGoogleActivity<*>.showRatingIntent(applicationId: String, marketName: String, onFailed: (() -> Unit)? = null) {
    when(marketName.lowercase()) {
        CAFE_BAZAAR -> showCafeBazaarIntent(applicationId) { onFailed?.invoke() }
        MYKET -> showMyketIntent(applicationId) { onFailed?.invoke() }
        GALAXY_STORE -> showGalaxyStoreIntent(applicationId) { onFailed?.invoke() }
        else -> {
            onFailed?.invoke()
        }
    }
}

fun WhyGoogleActivity<*>.showRatingBottomSheet(applicationId: String, marketName: String, callback: ((hasRated: Boolean) -> Unit)? = null) {
    if (!MarketRating.getUserHasRated(this)) {
        trying {
            MarketRatingBottomSheet(
                activity = this,
                applicationId = applicationId,
                marketName = marketName,
                onOptionsClicked = callback
            ).show(this.supportFragmentManager, MarketRatingBottomSheet::class.java.simpleName)
        }
    } else {
        callback?.invoke(true)
    }
}

@Deprecated(
    message = "pass the market name parameter to handle which market's intent should be call.",
    replaceWith = ReplaceWith("use showRatingBottomSheet function with applicationId, marketName and callback parameters.")
)
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

fun WhyGoogleActivity<*>.showGalaxyStoreIntent(
    applicationId: String,
    onFailed: (() -> Unit)? = null
) {
    try {
        val ai: ApplicationInfo = packageManager.getApplicationInfo(
            "com.sec.android.app.samsungapps",
            PackageManager.GET_META_DATA
        )
        val inappReviewVersion =
            ai.metaData.getInt("com.sec.android.app.samsungapps.review.inappReview", 0)
        if (inappReviewVersion > 0) {
            val intent = Intent("com.sec.android.app.samsungapps.REQUEST_INAPP_REVIEW_AUTHORITY")
            intent.setPackage("com.sec.android.app.samsungapps")
            intent.putExtra("callerPackage", applicationId) // applicationId : package name

            sendBroadcast(intent)
            val filter = IntentFilter()
            filter.addAction("com.sec.android.app.samsungapps.RESPONSE_INAPP_REVIEW_AUTHORITY")
            object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
//                    val hasAuthority = intent?.getBooleanExtra("hasAuthority", false)

                    val deeplinkUri = intent?.getStringExtra("deeplinkUri")
                    val newIntent = Intent()
                    newIntent.data = Uri.parse(deeplinkUri)

                    newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                    startActivity(newIntent)
                }
            }
        } else {
            onFailed?.invoke()
        }
    } catch (e: Exception) {
        Log.e("MarketIntent", "startMarketIntent: GalaxyStore => ${e.printStackTrace()}")
        onFailed?.invoke()
    }
}