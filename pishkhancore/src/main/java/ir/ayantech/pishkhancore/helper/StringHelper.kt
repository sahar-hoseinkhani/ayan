package ir.ayantech.pishkhancore.helper

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import ir.ayantech.whygoogle.helper.openPhoneWithNumber
import ir.ayantech.whygoogle.helper.openUrl

fun String.handlePishkhanLink(context: Context?) {
    when {
        this.startsWith("fragment:") -> {
//            this.replace("fragment:", "").let {
//                trying {
//                    it.let {
//                        context?.start(
//                            Class.forName(it).newInstance() as WhyGoogleFragment<*>
//                        )
//                    }
//                }
//            }
        }
        this.startsWith("bottomSheet:") -> {
//            trying {
//                (Class.forName(this.replace("bottomSheet:", "")).constructors.firstOrNull()
//                    ?.newInstance(context, "") as BaseBottomSheet<*>).show()
//            }
        }
        this.startsWith("call:") -> {
            this.replace("call:", "").let {
                it.openPhoneWithNumber(context)
            }
        }
        this.startsWith("chromeCustomTabs:") -> {
            this.replace("chromeCustomTabs:", "").openUrlViaChromeCustomTab(context)
        }
        else -> this.openUrl(context)
    }
}

fun String.openUrlViaChromeCustomTab(context: Context?, color: String? = null) {
    val customTabsIntent = CustomTabsIntent.Builder().also {
        it.setShowTitle(true)
    }.build()
    context?.let { customTabsIntent.launchUrl(it, Uri.parse(this)) }
}

fun String.removePishkhanFromString() =
    this.replace("با پیشخوان 24", "")
        .replace("پیشخوان 24", "")
        .replace("با پیشخوان24", "")
        .replace("پیشخوان24", "")
        .replace("با پیشخوان", "")
        .replace("پیشخوان", "")
        .trim()