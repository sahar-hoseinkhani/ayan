import android.content.Context
import android.provider.Settings
import ir.ayantech.pishkhancore.storage.PreferencesManager
import java.util.*

internal class PishkhanUser {
    companion object {
        const val PISHKHAN_USER_SESSION = "pishkhanUserSession"

        internal fun saveSession(context: Context, session: String) {
            PreferencesManager.getInstance(context)
                .saveToSharedPreferences(PISHKHAN_USER_SESSION, session)
        }

        internal fun getSession(context: Context): String {
            return PreferencesManager.getInstance(context)
                .readStringFromSharedPreferences(PISHKHAN_USER_SESSION)
        }
    }
}