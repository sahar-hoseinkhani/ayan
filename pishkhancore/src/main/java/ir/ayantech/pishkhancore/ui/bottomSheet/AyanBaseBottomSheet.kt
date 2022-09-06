package ir.ayantech.pishkhancore.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ayantech.whygoogle.helper.viewBinding

abstract class AyanBaseBottomSheet<T : ViewBinding> : BottomSheetDialogFragment() {

    val binding: T by viewBinding(binder)

    abstract val binder: (LayoutInflater) -> T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root
}
