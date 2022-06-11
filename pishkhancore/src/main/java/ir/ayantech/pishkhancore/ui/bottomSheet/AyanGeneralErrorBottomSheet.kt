package ir.ayantech.pishkhancore.ui.bottomSheet

import android.content.Context
import android.view.LayoutInflater
import ir.ayantech.pishkhancore.databinding.BottomSheetAyanErrorBinding
import ir.ayantech.whygoogle.helper.SimpleCallBack

class AyanGeneralErrorBottomSheet(
    context: Context,
    private val title: String,
    private val message: String,
    private val buttonText: String,
    private val retry: SimpleCallBack
) : AyanBaseBottomSheet<BottomSheetAyanErrorBinding>(context) {

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate() {
        super.onCreate()
        binding.errorTv.text = title
        binding.messageTv.text = message
        binding.retryTv.text = buttonText
        binding.retryTv.setOnClickListener {
            dismiss()
            retry()
        }

//        setOnCancelListener {
//            onErrorBottomSheetCancelled?.invoke()
//        }
    }

    override val binder: (LayoutInflater) -> BottomSheetAyanErrorBinding
        get() = BottomSheetAyanErrorBinding::inflate

}