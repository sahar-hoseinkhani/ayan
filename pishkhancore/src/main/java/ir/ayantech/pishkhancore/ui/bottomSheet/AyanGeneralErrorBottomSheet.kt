package ir.ayantech.pishkhancore.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import ir.ayantech.pishkhancore.databinding.BottomSheetAyanErrorBinding
import ir.ayantech.whygoogle.helper.SimpleCallBack
import ir.ayantech.whygoogle.helper.nullableFragmentArgument
import java.io.Serializable

class AyanGeneralErrorBottomSheet() : AyanBaseBottomSheet<BottomSheetAyanErrorBinding>() {

    @Deprecated(message = "passing parameters in constructor is Deprecated. create an instance of class and pass parameters with one of kotlin scope functions like also scope function.", level = DeprecationLevel.ERROR)
    constructor(
        title: String,
        message: String,
        buttonText: String,
        setCancelable: Boolean = false,
        retry: SimpleCallBack = {}
    ) : this() {
        this.title = title
        this.message = message
        this.buttonText = buttonText
        this.setCancelable = setCancelable
        this.retry = retry as Serializable
    }

    var title: String? by nullableFragmentArgument(null)
    var message: String? by nullableFragmentArgument(null)
    var buttonText: String? by nullableFragmentArgument(null)
    var setCancelable: Boolean by nullableFragmentArgument(false)
    var retry: Serializable by nullableFragmentArgument(null)

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
            (retry as? SimpleCallBack)?.invoke()
        }
    }

    override val binder: (LayoutInflater) -> BottomSheetAyanErrorBinding
        get() = BottomSheetAyanErrorBinding::inflate

}