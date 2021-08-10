import android.content.Context
import android.provider.Settings
import ir.ayantech.pishkhancore.storage.PreferencesManager
import java.util.*

internal class PishkhanUser {
    companion object {
        const val PISHKHAN_USER_SESSION = "pishkhanUserSession"
        const val PISHKAHN_USER_APPLICATION_UNIQUE_ID = "pishkhanUserApplicationUniqueId"


        internal fun saveSession(context: Context, session: String) {
            PreferencesManager.getInstance(context).saveToSharedPreferences(PISHKHAN_USER_SESSION, session)
        }

        internal fun getSession(context: Context): String {
            return PreferencesManager.getInstance(context).readStringFromSharedPreferences(PISHKHAN_USER_SESSION)
        }

        private fun saveApplicationUniqueId(context: Context, applicationUniqueId: String) {
            PreferencesManager.getInstance(context)
                .saveToSharedPreferences(PISHKAHN_USER_APPLICATION_UNIQUE_ID, applicationUniqueId)
        }

        internal fun getApplicationUniqueId(context: Context): String {
            return if (PreferencesManager.getInstance(context).readStringFromSharedPreferences(
                    PISHKAHN_USER_APPLICATION_UNIQUE_ID
                ).isNotEmpty()
            )
                PreferencesManager.getInstance(context).readStringFromSharedPreferences(PISHKAHN_USER_APPLICATION_UNIQUE_ID)
            else
                createApplicationUniqueId(context).also { saveApplicationUniqueId(context, it) }
        }

        private fun createApplicationUniqueId(context: Context) =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                ?: UUID.randomUUID().toString()
    }
}