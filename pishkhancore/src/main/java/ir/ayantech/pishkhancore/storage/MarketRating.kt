package ir.ayantech.pishkhancore.storage

import android.content.Context

internal class MarketRating {

    companion object {
        private const val USER_HAS_RATED = "userHasRated"

        internal fun saveUserHasRated(context: Context, hasRated: Boolean) {
            PreferencesManager.getInstance(context)
                .saveToSharedPreferences(USER_HAS_RATED, hasRated)
        }

        internal fun getUserHasRated(context: Context) = PreferencesManager.getInstance(context)
                .readBooleanFromSharedPreferences(USER_HAS_RATED)
    }
}