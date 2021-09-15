package ir.ayantech.pishkhancore.core

import android.app.Application
import android.content.Context
import ir.ayantech.ayannetworking.api.AyanApi
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.ayannetworking.api.SimpleCallback
import ir.ayantech.ayannetworking.ayanModel.LogLevel
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanCheckStatusBottomSheet
import ir.ayantech.pishkhancore.ui.fragment.AyanHistoryFragment
import ir.ayantech.pushsdk.core.AyanNotification
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.BooleanCallBack

object PishkhanCore {
    var applicationUniqueToken: String? = null
    var ayanApi: AyanApi? = null

    fun initialize(
        application: Application,
        applicationUniqueToken: String,
    ) {
        this.applicationUniqueToken = applicationUniqueToken
        AyanNotification.initialize(application)
        AyanNotification.reportExtraInfo(AppExtraInfo(PishkhanUser.getSession(application)))
        this.ayanApi = AyanApi(
            application,
            { PishkhanUser.getSession(application) },
            "https://application.monshiplus.ayantech.ir/WebServices/App.svc/",
            logLevel = LogLevel.LOG_ALL
        )
    }

    fun startPishkhanLogin(
        activity: WhyGoogleActivity<*>,
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
        activity: WhyGoogleActivity<*>,
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
        activity: WhyGoogleActivity<*>,
        changeStatus: OnChangeStatus,
        failure: OnFailure
    ) {
        AyanPayment.onlinePaymentBills(bills, product, activity, changeStatus, failure)
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
        respCallback: (AppConfigAdvertisementOutput) -> Unit
    ) {
        AyanAppConfigAdvertisement.getAppConfigAdvertisement(respCallback)
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