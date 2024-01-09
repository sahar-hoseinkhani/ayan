package ir.ayantech.pishkhancore.ui.bottomSheet

import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.getPassportStatusFromPost
import ir.ayantech.pishkhancore.helper.textChanges
import ir.ayantech.pishkhancore.model.post_package.InquiryPostPackageTrackingOutput
import ir.ayantech.whygoogle.helper.trying

fun MaterialDialog.postInquiryBottomSheet(navigateToPostResult: (InquiryPostPackageTrackingOutput?) -> Unit) {
    cornerRadius(8f)
    customView(R.layout.bottom_sheet_post_inquiry)
    show {
        trying {
            val postInquiryButton =
                getCustomView().findViewById<AppCompatButton>(R.id.btn_post_inquiry)
            val postalCodeInput = getCustomView().findViewById<EditText>(R.id.postalCodeET)

            postalCodeInput.textChanges {
                postInquiryButton.isEnabled = it.length > 6
            }
            postInquiryButton.setOnClickListener {
                getPassportStatusFromPost(
                    packageNumber = postalCodeInput.text.toString(),
                    navigateToPostResult = {
                        navigateToPostResult(it)
                        dismiss()
                    }
                )
            }
        }
    }
}