package ir.ayantech.pishkhancore.helper

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.textChanges(onTextChangedCallback: (() -> Unit)? = null, callback: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            callback(p0?.toString() ?: "")
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChangedCallback?.invoke()
        }
    })
}

fun EditText.placeCursorToEnd() {
    this.setSelection(this.text.length)
}