package ir.ayantech.pishkhancore.helper

import android.content.Context
import ir.ayantech.pishkhancore.R

internal class InformationHelper {
    companion object {
        fun getApplicationNameForPishkhan(context: Context) =
            context.resources.getString(R.string.applicationNameForPishkhan)

        fun getApplicationName(context: Context) =
            context.resources.getString(R.string.applicationName)

        fun getApplicationType(context: Context) =
            context.resources.getString(R.string.applicationType)

        fun getApplicationVersion(context: Context): String =
            context.packageManager.getPackageInfo(context.packageName, 0).versionName

        fun getApplicationCategory(applicationUniqueToken: String) =
            when (applicationUniqueToken.lowercase()) {
                "playstore" -> "playstore"
                "charkhoneh" -> "charkhoneh"
                "cafebazaar" -> "cafebazaar"
                "myket" -> "myket"
                "bulk" -> "bulk"
                "galaxystore" -> "galaxystore"
                "xiaomistore" -> "XiaomiStore"
                else -> "socialmedia"
            }
    }
}