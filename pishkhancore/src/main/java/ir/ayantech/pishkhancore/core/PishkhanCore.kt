package ir.ayantech.pishkhancore.core

import PishkhanUser
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import ir.ayantech.ayannetworking.api.AyanApi
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.ayannetworking.api.SimpleCallback
import ir.ayantech.ayannetworking.ayanModel.LogLevel
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanCheckStatusBottomSheet
import ir.ayantech.pishkhancore.ui.fragment.AyanHistoryFragment
import ir.ayantech.pushsdk.core.AyanNotification
import ir.ayantech.pushsdk.networking.PushNotificationNetworking
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
        pushNotificationUrl: String
    ){
        this.applicationUniqueToken = applicationUniqueToken
        this.baseUrl = baseUrl
        this.serviceBaseUrl = serviceBaseUrl
        this.versionControllingBaseUrl = versionControllingBaseUrl
        AyanNotification.initialize(application)
        PushNotificationNetworking.ayanApi.defaultBaseUrl  = pushNotificationUrl
        AyanNotification.reportExtraInfo(AppExtraInfo(PishkhanUser.session))
        this.ayanApi = AyanApi(
            application,
            { PishkhanUser.session },
            baseUrl,
            logLevel = LogLevel.LOG_ALL
        )
    }

    fun startPishkhanLogin(
        activity: AppCompatActivity,
        additionalData: String? = null,
        mobileNumber: String? = null,
        referenceToken: String? = null,
        callback: BooleanCallBack
    ) {
        applicationUniqueToken?.let {
            AyanCheckStatusBottomSheet(
                activity,
                it,
                additionalData,
                mobileNumber,
                referenceToken,
                callback
            ).show()
        }
    }

    fun getUserToken(context: Context): String = PishkhanUser.session

    fun shareApp(context: Context) {
        applicationUniqueToken?.let { VersionControl.shareApp(context, it) }
    }

    fun getDownloadLink(context: Context, callback: (downloadLink: String) -> Unit) {
        applicationUniqueToken?.let { VersionControl.getDownloadLink(context, it, callback) }
    }

    fun logout(context: Context) {
//        PishkhanUser.saveSession(context, "")
        PishkhanUser.session = ""
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
        context: Context,
        respCallback: (AppConfigAdvertisementOutput) -> Unit
    ) {
        AyanAppConfigAdvertisement.getAppConfigAdvertisement(context, respCallback)
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