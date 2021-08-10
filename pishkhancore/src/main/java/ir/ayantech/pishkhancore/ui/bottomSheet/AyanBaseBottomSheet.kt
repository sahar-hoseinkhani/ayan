package ir.ayantech.pishkhancore.ui.bottomSheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import ir.ayantech.pishkhancore.R
import ir.ayantech.whygoogle.helper.viewBinding

abstract class AyanBaseBottomSheet<T : ViewBinding>(context: Context) : BottomSheetDialog(context) {

    val binding: T by viewBinding(binder)

    abstract val binder: (LayoutInflater) -> T

    init {
        setOnShowListener { onCreate() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window?.setLayout(MATCH_PARENT, MATCH_PARENT)
        if (isFullScreen()) {
            val bottomSheet =
                this.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            BottomSheetBehavior.from<FrameLayout?>(bottomSheet!!).state =
                BottomSheetBehavior.STATE_EXPANDED
        }
    }

    open fun isFullScreen() = true

    open fun onCreate() {
    }
}
