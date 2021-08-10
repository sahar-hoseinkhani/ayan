package ir.ayantech.pishkhancore.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.viewbinding.ViewBinding
import ir.ayantech.pishkhancore.R
import ir.ayantech.whygoogle.helper.viewBinding

abstract class AyanBaseDialog<T : ViewBinding>(context: Context) :
    Dialog(context, R.style.AyanDialog) {

    val binding: T by viewBinding(binder)

    abstract val binder: (LayoutInflater) -> T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(R.drawable.back_dialog)
        setContentView(binding.root)
    }
}