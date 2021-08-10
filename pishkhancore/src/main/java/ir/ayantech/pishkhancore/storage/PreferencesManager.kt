package ir.ayantech.pishkhancore.storage

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager private constructor(context: Context) {

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    companion object {
        private var preferencesManager: PreferencesManager? = null

        fun getInstance(context: Context): PreferencesManager =
            preferencesManager ?: PreferencesManager(context).also { preferencesManager = it }
    }

    fun saveToSharedPreferences(fieldName: String, value: String) {
        sharedPreferences?.edit()?.putString(fieldName, value)?.apply()
    }

    fun saveToSharedPreferences(fieldName: String, value: Boolean) {
        sharedPreferences?.edit()?.putBoolean(fieldName, value)?.apply()
    }

    fun saveToSharedPreferences(fieldName: String, value: Long?) {
        sharedPreferences?.edit()?.putLong(fieldName, value!!)?.apply()
    }

    fun readStringFromSharedPreferences(field: String): String {
        return sharedPreferences?.getString(field, "") ?: ""
    }

    fun readBooleanFromSharedPreferences(field: String): Boolean {
        return sharedPreferences?.getBoolean(field, false) ?: false
    }

    fun readLongFromSharedPreferences(field: String): Long {
        return sharedPreferences?.getLong(field, 0L) ?: 0L
    }
}