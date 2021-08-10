package ir.ayantech.pishkhancore.ui.bottomSheet

import android.content.Context
import android.view.LayoutInflater
import ir.ayantech.pishkhancore.databinding.BottomSheetAyanErrorBinding
import ir.ayantech.whygoogle.helper.SimpleCallBack

class AyanErrorBottomSheet(
    context: Context,
    private val message: String,
    private val onErrorBottomSheetCancelled: SimpleCallBack? = null,
    private val retry: SimpleCallBack
) : AyanBaseBottomSheet<BottomSheetAyanErrorBinding>(context) {

    override fun onCreate() {
        super.onCreate()
        binding.messageTv.text = message
        binding.retryTv.setOnClickListener {
            dismiss()
            retry()
        }

        setOnCancelListener {
            onErrorBottomSheetCancelled?.invoke()
        }
    }

    override val binder: (LayoutInflater) -> BottomSheetAyanErrorBinding
        get() = BottomSheetAyanErrorBinding::inflate

}