package ir.ayantech.pishkhancore.core

import PishkhanUser
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ayantech.ayannetworking.api.AyanApi
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.ayannetworking.api.SimpleCallback
import ir.ayantech.ayannetworking.ayanModel.LogLevel
import ir.ayantech.pishkhancore.BuildConfig
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanCheckStatusBottomSheet
import ir.ayantech.pishkhancore.ui.fragment.AyanHistoryFragment
import ir.ayantech.pishkhancore.ui.fragment.AyanRulesFragment
import ir.ayantech.pushsdk.core.AyanNotification
import ir.ayantech.pushsdk.networking.PushNotificationNetworking
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.BooleanCallBack
import ir.ayantech.whygoogle.standard.WhyGoogleInterface

object PishkhanCore {
    var applicationUniqueToken: String? = null
    var baseUrl: String? = null
    var serviceBaseUrl: String? = null
    var versionControllingBaseUrl: String? = null
    var ayanApi: AyanApi? = null

    fun initialize(
        application: Application,
        applicationUniqueToken: String,
        baseUrl: String,
        serviceBaseUrl: String,
        versionControllingBaseUrl: String,
        pushNotificationUrl: String,
        headers: HashMap<String, String> = hashMapOf(),
        showLogs: Boolean = false,
    ) {
        this.applicationUniqueToken = applicationUniqueToken
        this.baseUrl = baseUrl
        this.serviceBaseUrl = serviceBaseUrl
        this.versionControllingBaseUrl = versionControllingBaseUrl
        AyanNotification.initialize(application)
        PushNotificationNetworking.ayanApi.defaultBaseUrl = pushNotificationUrl
        AyanNotification.reportExtraInfo(AppExtraInfo(PishkhanUser.getSession(application)))
        this.ayanApi = AyanApi(
            application,
            { PishkhanUser.getSession(application) },
            baseUrl,
            logLevel = if (showLogs) LogLevel.LOG_ALL else LogLevel.DO_NOT_LOG,
            headers = headers
        )
    }

    fun startPishkhanLogin(
        activity: AppCompatActivity,
        additionalData: String? = null,
        mobileNumber: String? = null,
        referenceToken: String? = null,
        callback: BooleanCallBack
    ): BottomSheetDialogFragment? {
        var bsheet: BottomSheetDialogFragment? = null
        applicationUniqueToken?.let {
            bsheet = AyanCheckStatusBottomSheet(
                activity,
                it,
                additionalData,
                mobileNumber,
                referenceToken,
                callback
            )
        }
        return bsheet
    }

    fun loginPishkhan(
        activity: AppCompatActivity,
        additionalData: String? = null,
        mobileNumber: String? = null,
        referenceToken: String? = null,
        changeStatus: OnChangeStatus,
        failure: OnFailure,
        callback: BooleanCallBack
    ) {
        applicationUniqueToken?.let {
            val versionControl =
                VersionControl(activity, it, changeStatus = changeStatus, failure = failure)
            versionControl.checkForNewVersion { updateNotRequired ->
                when (updateNotRequired) {
                    true -> if (PishkhanUser.getSession(activity).isEmpty()) {
                        AyanLogin.login(
                            activity,
                            additionalData,
                            mobileNumber,
                            referenceToken,
                            changeStatus,
                            failure
                        ) {
                            callback.invoke(it)
                        }
                    } else {
                        callback.invoke(true)
                    }
                    else -> activity.finish()
                }
            }
        }
    }

    fun getUserToken(context: Context): String = PishkhanUser.getSession(context)

    fun shareApp(context: Context) {
        applicationUniqueToken?.let { VersionControl.shareApp(context, it) }
    }

    fun getDownloadLink(context: Context, callback: (downloadLink: String) -> Unit) {
        applicationUniqueToken?.let { VersionControl.getDownloadLink(context, it, callback) }
    }

    fun logout(context: Context) {
        PishkhanUser.saveSession(context, "")
    }

    fun startHistoryFragment(
        activity: WhyGoogleInterface,
        fragment: AyanHistoryFragment,
        changeStatus: OnChangeStatus,
        failure: OnFailure,
        onDetailsClicked: (PaymentHistoryGetTransactionInfoOutput) -> Unit
    ) {
        activity.start(fragment.also {
            it.onDetailsClicked = onDetailsClicked
            it.changeStatus = changeStatus
            it.failure = failure
        })
    }

    fun startRulesFragment(
        activity: WhyGoogleInterface,
        fragment: AyanRulesFragment,
        changeStatus: OnChangeStatus,
        failure: OnFailure
    ) {
        activity.start(fragment.also {
            it.changeStatus = changeStatus
            it.failure = failure
        })
    }

    fun onlinePaymentBills(
        bills: List<String>,
        product: String,
        context: Context,
        changeStatus: OnChangeStatus,
        failure: OnFailure,
        mobilePhone: String? = null
    ) {
        AyanPayment.onlinePaymentBills(bills, product, mobilePhone, context, changeStatus, failure)
    }

    fun getInquiryHistory(
        product: String,
        changeStatus: OnChangeStatus,
        failure: OnFailure,
        callBack: (List<InquiryHistory>?) -> Unit
    ) {
        AyanInquiryHistory.getInquiryHistoryList(product, changeStatus, failure) { list ->
            callBack.invoke(list)
        }
    }

    fun removeInquiryHistoryItem(
        uniqueId: String, changeStatus: OnChangeStatus,
        failure: OnFailure, removeSuccessCallback: SimpleCallback
    ) {
        AyanInquiryHistory.removeInquiryHistoryItem(uniqueId, changeStatus, failure) {
            removeSuccessCallback.invoke()
        }
    }

    fun getAppConfigAdvertisement(
        activity: WhyGoogleActivity<*>,
        respCallback: (AppConfigAdvertisementOutput) -> Unit
    ) {
        AyanAppConfigAdvertisement.getAppConfigAdvertisement(activity, respCallback)
    }

    fun getAppConfigBasicInformation(
        changeStatus: OnChangeStatus,
        failure: OnFailure,
        callBack: (AppConfigBasicInformationOutput) -> Unit
    ) {
        AyanAppConfigBasicInformation.getAppConfigBasicInformation(
            changeStatus,
            failure
        ) { output ->
            callBack.invoke(output)
        }
    }
}