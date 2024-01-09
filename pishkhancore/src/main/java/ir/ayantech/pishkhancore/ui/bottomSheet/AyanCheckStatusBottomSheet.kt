package ir.ayantech.pishkhancore.ui.bottomSheet

import PishkhanUser
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.AyanCommonCallStatus
import ir.ayantech.ayannetworking.ayanModel.Failure
import ir.ayantech.ayannetworking.ayanModel.FailureType
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.core.VersionControl
import ir.ayantech.pishkhancore.databinding.BottomSheetAyanCheckStatusBinding
import ir.ayantech.pishkhancore.helper.InformationHelper
import ir.ayantech.pishkhancore.helper.nullableFragmentArgument
import ir.ayantech.pishkhancore.model.EndPoint
import ir.ayantech.pishkhancore.model.LoginInput
import ir.ayantech.pishkhancore.model.LoginOutput
import ir.ayantech.whygoogle.helper.BooleanCallBack
import ir.ayantech.whygoogle.helper.makeGone
import ir.ayantech.whygoogle.helper.makeVisible
import java.io.Serializable

class AyanCheckStatusBottomSheet(
//    private var activity: AppCompatActivity,
//    applicationUniqueToken: String,
//    private val additionalData: String? = null,
//    private val mobileNumber: String? = null,
//    private val referenceToken: String? = null,
//    private val callBack: BooleanCallBack
) : AyanBaseBottomSheet<BottomSheetAyanCheckStatusBinding>() {

    @Deprecated(message = "passing parameters in constructor is Deprecated. create an instance of class and pass parameters with one of kotlin scope functions like also scope function.", level = DeprecationLevel.ERROR)
    constructor(
        activity: AppCompatActivity,
        applicationUniqueToken: String,
        additionalData: String? = null,
        mobileNumber: String? = null,
        referenceToken: String? = null,
        callBack: BooleanCallBack
    ) : this() {
//        this.appCompatActivity = activity as Serializable
        this.applicationUniqueToken = applicationUniqueToken
        this.additionalData = additionalData
        this.mobileNumber = mobileNumber
        this.referenceToken = referenceToken
        this.callBack = callBack as Serializable
    }

//    var appCompatActivity : Serializable? by nullableFragmentArgument(null)
    var applicationUniqueToken : String? by nullableFragmentArgument(null)
    var additionalData : String? by nullableFragmentArgument(null)
    var mobileNumber : String? by nullableFragmentArgument(null)
    var referenceToken : String? by nullableFragmentArgument(null)
    var callBack : Serializable? by nullableFragmentArgument(null)

    init {
        isCancelable = false
    }

    private val ayanCommonCallingStatus = AyanCommonCallStatus {
        failure {
            showNoInternetLayout(it)
        }
    }

    private val versionControl: VersionControl
        get() = VersionControl(requireActivity() as AppCompatActivity, applicationUniqueToken ?: "", ayanCommonCallingStatus)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        versionControl.checkForNewVersion { updateNotRequired ->
            when (updateNotRequired) {
                true -> if (PishkhanUser.getSession(requireActivity()).isEmpty()) {
                    login(additionalData, mobileNumber, referenceToken)
                } else {
                    (callBack as? BooleanCallBack)?.invoke(true)
                    dismiss()
                }
                else -> requireActivity().finish()
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
                        PishkhanUser.saveSession(requireActivity(), it.Token)
//                        PishkhanUser.saveSession(activity, "06898F446CB84D1E8E18B02301C35D91")
                        (callBack as? BooleanCallBack)?.invoke(true)
                        dismiss()
                    }
                }
            }, EndPoint.Login,
            LoginInput(
                additionalData,
                ApplicationName = InformationHelper.getApplicationNameForPishkhan(requireActivity()),
                Channel = PishkhanCore.applicationUniqueToken?.let {
                    InformationHelper.getApplicationCategory(
                        it
                    )
                },
                mobileNumber,
                referenceToken,
                InformationHelper.getApplicationVersion(requireActivity())
            ), commonCallStatus = ayanCommonCallingStatus,
            baseUrl = PishkhanCore.serviceBaseUrl!!,
            hasIdentity = false
        )
    }

    private fun showNoInternetLayout(failure: Failure) {
        binding.apply {
            descriptionTv.makeGone()
            waitTv.makeGone()
            retryRl.makeVisible()
            errorTv.text =
                if (failure.failureType == FailureType.CANCELED)
                    requireActivity().resources.getString(R.string.custom_error_message)
                else
                    failure.failureMessage
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