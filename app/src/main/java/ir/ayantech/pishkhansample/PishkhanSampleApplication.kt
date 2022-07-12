package ir.ayantech.pishkhansample

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhansample.BuildConfig.category

class PishkhanSampleApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        PishkhanCore.initialize(this, category,
            baseUrl =  "https://application.monshiplus.ayantech.ir/WebServices/App.svc/",
            serviceBaseUrl = "https://application.monshiplus.ayantech.ir/WebServices/Services.svc/",
            versionControllingBaseUrl = "https://versioncontrol.infra.ayantech.ir/WebServices/App.svc/",
            pushNotificationUrl = "https://pushnotification.infra.ayantech.ir/WebServices/App.svc/")
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}