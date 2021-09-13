package ir.ayantech.pishkhancore.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.databinding.DialogSaveInquiryBinding
import ir.ayantech.pishkhancore.helper.InquiryHistoryCallBack
import ir.ayantech.pishkhancore.helper.placeCursorToEnd
import ir.ayantech.pishkhancore.model.*

class AyanSaveInquiryDialog(
    context: Context,
    private val inquiryHistory: InquiryHistory,
    private val onChange: InquiryHistoryCallBack
) : AyanBaseDialog<DialogSaveInquiryBinding>(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            productIv.setImageResource(inquiryHistory.Type.Name.getProductIcon())
            valueTv.text =
                inquiryHistory.Type.Name.getProductShowName() + " - " + inquiryHistory.QueryValue

            closeIv.setOnClickListener {
                dismiss()
            }

            saveNameEt.setText(inquiryHistory.Note)
            saveNameEt.placeCursorToEnd()

            saveBtn.setOnClickListener {
                if (saveNameEt.text.toString().isEmpty()) {
                    saveNameEt.error = "نام انتخابی نباید خالی باشد"
                    return@setOnClickListener
                }
                PishkhanCore.ayanApi?.simpleCall<InquiryHistory>(
                    EndPoint.InquiryBookmarkItem,
                    InquiryBookmarkItemInput(
                        inquiryHistory.Favorite,
                        inquiryHistory.NotificationPermission,
                        inquiryHistory.Type.Name,
                        saveNameEt.text.toString(),
                        inquiryHistory.UniqueID,
                    )
                ) {
                    dismiss()
                    it?.let { inquiryHistory ->
                        onChange(inquiryHistory)
                    }
                }
            }

            removeBtn.setOnClickListener {
                PishkhanCore.ayanApi?.simpleCall<Void>(
                    EndPoint.InquiryDeleteRecentListItem,
                    InquiryDeleteRecentListItemInput(inquiryHistory.UniqueID)
                ) {
                    dismiss()
                    onChange(null)
                }
            }
        }
    }

    override val binder: (LayoutInflater) -> DialogSaveInquiryBinding
        get() = DialogSaveInquiryBinding::inflate
}