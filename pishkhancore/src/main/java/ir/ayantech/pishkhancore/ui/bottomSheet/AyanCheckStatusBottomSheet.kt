package ir.ayantech.pishkhancore.ui.bottomSheet

import PishkhanUser
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.AyanCommonCallStatus
import ir.ayantech.ayannetworking.ayanModel.Failure
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.core.VersionControl
import ir.ayantech.pishkhancore.databinding.BottomSheetAyanCheckStatusBinding
import ir.ayantech.pishkhancore.helper.InformationHelper
import ir.ayantech.pishkhancore.model.EndPoint
import ir.ayantech.pishkhancore.model.LoginInput
import ir.ayantech.pishkhancore.model.LoginOutput
import ir.ayantech.whygoogle.helper.BooleanCallBack
import ir.ayantech.whygoogle.helper.makeGone
import ir.ayantech.whygoogle.helper.makeVisible

class AyanCheckStatusBottomSheet(
    private var activity: AppCompatActivity,
    applicationUniqueToken: String,
    private val additionalData: String? = null,
    private val mobileNumber: String? = null,
    private val referenceToken: String? = null,
    private val callBack: BooleanCallBack
) : AyanBaseBottomSheet<BottomSheetAyanCheckStatusBinding>(activity) {

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    private val ayanCommonCallingStatus = AyanCommonCallStatus {
        failure {
            showNoInternetLayout(it)
        }
    }

    private val versionControl =
        VersionControl(activity, applicationUniqueToken, ayanCommonCallingStatus)

    override fun onCreate() {
        super.onCreate()

        versionControl.checkForNewVersion { updateNotRequired ->
            when (updateNotRequired) {
                true -> if (PishkhanUser.getSession(activity).isEmpty()) {
                    login(additionalData, mobileNumber, referenceToken)
                } else {
                    callBack.invoke(true)
                    dismiss()
                }
                else -> activity.finish()
            }
        }
    }

    private fun login(
        additionalData: String?,
        mobileNumber: String?,
        referenceToken: String?
    ) {
        PishkhanCore.ayanApi?.ayanCall<LoginOutput>(
            AyanCallStatus {
                success {
                    it.response?.Parameters?.let {
                        PishkhanUser.saveSession(activity, it.Token)
//                        PishkhanUser.saveSession(activity, "06898F446CB84D1E8E18B02301C35D91")
                        callBack.invoke(true)
                        dismiss()
                    }
                }
            }, EndPoint.Login,
            LoginInput(
                additionalData,
                ApplicationName = InformationHelper.getApplicationNameForPishkhan(activity),
                Channel = PishkhanCore.applicationUniqueToken?.let {
                    InformationHelper.getApplicationCategory(
                        it
                    )
                },
                mobileNumber,
                referenceToken,
                InformationHelper.getApplicationVersion(activity)
            ), commonCallStatus = ayanCommonCallingStatus,
            baseUrl = "https://application.monshiplus.ayantech.ir/WebServices/Services.svc/",
            hasIdentity = false
        )
    }

    private fun showNoInternetLayout(failure: Failure) {
        binding.apply {
            descriptionTv.makeGone()
            waitTv.makeGone()
            retryRl.makeVisible()
            errorTv.text = failure.failureMessage
            retryTv.setOnClickListener {
                descriptionTv.makeVisible()
                waitTv.makeVisible()
                retryRl.makeGone()
                failure.reCallApi()
            }
        }
    }

    override val binder: (LayoutInflater) -> BottomSheetAyanCheckStatusBinding
        get() = BottomSheetAyanCheckStatusBinding::inflate
}