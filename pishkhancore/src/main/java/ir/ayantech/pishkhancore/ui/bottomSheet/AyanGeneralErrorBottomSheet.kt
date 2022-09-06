package ir.ayantech.pishkhancore.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import ir.ayantech.pishkhancore.databinding.BottomSheetAyanErrorBinding
import ir.ayantech.whygoogle.helper.SimpleCallBack

class AyanGeneralErrorBottomSheet(
    private val title: String,
    private val message: String,
    private val buttonText: String,
    private val setCancelable: Boolean = false,
    private val retry: SimpleCallBack = {}
) : AyanBaseBottomSheet<BottomSheetAyanErrorBinding>() {

    init {
        isCancelable = setCancelable
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.errorTv.text = title
        binding.messageTv.text = message
        binding.retryTv.text = buttonText
        binding.retryTv.setOnClickListener {
            dismiss()
            retry()
        }
    }

    override val binder: (LayoutInflater) -> BottomSheetAyanErrorBinding
        get() = BottomSheetAyanErrorBinding::inflate

}