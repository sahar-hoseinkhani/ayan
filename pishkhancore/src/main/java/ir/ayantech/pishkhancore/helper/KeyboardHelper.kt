package ir.ayantech.pishkhancore.helper

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.trying

fun WhyGoogleActivity<*>.hideKeyboard(view: View? = null) {
    trying {
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentView = view ?: this.currentFocus ?: View(this)
        inputMethodManager.hideSoftInputFromWindow(currentView.windowToken, 0)
    }
}

fun WhyGoogleActivity<*>.showKeyboard(view: View? = null) {
    trying {
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentView = view ?: this.currentFocus ?: View(this)
        inputMethodManager.showSoftInput(currentView, 0)
    }
}