package ir.ayantech.pishkhansample

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhansample.BuildConfig.category

class PishkhanSampleApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        PishkhanCore.initialize(this, category)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}