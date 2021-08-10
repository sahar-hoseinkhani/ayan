package ir.ayantech.pishkhancore.helper

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.openPhoneWithNumber
import ir.ayantech.whygoogle.helper.openUrl

fun String.handlePishkhanLink(activity: WhyGoogleActivity<*>?) {
    when {
        this.startsWith("fragment:") -> {
//            this.replace("fragment:", "").let {
//                trying {
//                    it.let {
//                        activity?.start(
//                            Class.forName(it).newInstance() as WhyGoogleFragment<*>
//                        )
//                    }
//                }
//            }
        }
        this.startsWith("bottomSheet:") -> {
//            trying {
//                (Class.forName(this.replace("bottomSheet:", "")).constructors.firstOrNull()
//                    ?.newInstance(activity, "") as BaseBottomSheet<*>).show()
//            }
        }
        this.startsWith("call:") -> {
            this.replace("call:", "").let {
                it.openPhoneWithNumber(activity)
            }
        }
        this.startsWith("chromeCustomTabs:") -> {
            this.replace("chromeCustomTabs:", "").openUrlViaChromeCustomTab(activity)
        }
        else -> this.openUrl(activity)
    }
}

fun String.openUrlViaChromeCustomTab(context: Context?, color: String? = null) {
    val customTabsIntent = CustomTabsIntent.Builder().also {
        it.setShowTitle(true)
    }.build()
    context?.let { customTabsIntent.launchUrl(it, Uri.parse(this)) }
}