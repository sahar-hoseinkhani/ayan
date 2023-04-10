package ir.ayantech.pishkhancore.ui.bottomSheet

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import ir.ayantech.advertisement.helper.trying
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.ui.fragment.LoginFragment
import ir.ayantech.whygoogle.activity.WhyGoogleActivity

fun MaterialDialog.showOptionalLoginBottomSheet(
    activity: WhyGoogleActivity<*>,
    changeStatus: OnChangeStatus,
    failure: OnFailure,
    afterLoginCallback: () -> Unit,
    @DrawableRes productImageResource: Int? = null,
    onEnterAppClick: (() -> Unit)? = null
) {
    cornerRadius(8f)
    customView(R.layout.bottom_sheet_optional_login)
    show {
        trying {
            val loginBtn = getCustomView().findViewById<AppCompatButton>(R.id.loginBtn)
            val enterBtn = getCustomView().findViewById<AppCompatButton>(R.id.enterBtn)

            loginBtn.setOnClickListener {
                PishkhanCore.startLoginFragment(
                    activity = activity,
                    fragment = LoginFragment(),
                    changeStatus = changeStatus,
                    failure = failure,
                    callback = afterLoginCallback,
                    productImageResource = productImageResource
                )
                dismiss()
            }

            enterBtn.setOnClickListener {
                onEnterAppClick?.invoke()
                dismiss()
            }
        }
    }
}