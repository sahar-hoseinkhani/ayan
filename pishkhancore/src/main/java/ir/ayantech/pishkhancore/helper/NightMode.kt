package ir.ayantech.pishkhancore.helper

import androidx.appcompat.app.AppCompatDelegate

fun setNightMode(isNightMode: Boolean) {
    AppCompatDelegate.setDefaultNightMode(if (isNightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
}