package ir.ayantech.pishkhansample

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import ir.ayantech.ayannetworking.ayanModel.LogLevel
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.core.PishkhanCore.baseUrl
import ir.ayantech.pishkhancore.core.PishkhanCore.versionControllingBaseUrl

class PishkhanSampleApplication : MultiDexApplication() {

    val VERSION_NAME = "2.6.1"

    // Field from product flavor: freewayToll-myket
    val applicationName = "aefreewaytollbillinquiry"

    // Field from default config.
    val applicationType = "android"

    // Field from product flavor: freewayToll-myket
    val baseUrl = "https://application.monshiplus.ayantech.ir/WebServices/App.svc/"

    // Field from product flavor: freewayToll-myket
    val category = "myket"

    // Field from product flavor: freewayToll-myket
    val pushNotificationUrl = "https://pushnotification.infra.ayantech.ir/WebServices/App.svc/"

    // Field from product flavor: freewayToll-myket
    val servicesBaseUrl = "https://application.monshiplus.ayantech.ir/WebServices/Services.svc/"

    // Field from product flavor: freewayToll-myket
    val vasHookUrl = "http://hook.vas.ayantech.ir/WebServices/App.svc/"

    // Field from product flavor: freewayToll-myket
    val versionControllingBaseUrl = "https://versioncontrol.infra.ayantech.ir/WebServices/App.svc/"

    override fun onCreate() {
        super.onCreate()
        PishkhanCore.initialize(
            application = this,
            applicationUniqueToken = "15B54590118E4DEE80DF0C12BAECD01D",
            baseUrl = baseUrl,
            serviceBaseUrl = servicesBaseUrl,
            versionControllingBaseUrl = versionControllingBaseUrl,
            pushNotificationUrl = pushNotificationUrl,
            showLogs = true
        )
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}