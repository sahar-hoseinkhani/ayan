package ir.ayantech.pishkhancore.core

import android.app.Application
import android.content.Context
import ir.ayantech.ayannetworking.api.AyanApi
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.ayanModel.LogLevel
import ir.ayantech.pishkhancore.model.AppExtraInfo
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanCheckStatusBottomSheet
import ir.ayantech.pishkhancore.ui.fragment.HistoryFragment
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

    fun startHistoryFragment(
        activity: WhyGoogleActivity<*>,
        transActionCategoryTypeName: String,
        transActionTypeName: String
    ) {
        activity.start(HistoryFragment().also {
            it.transActionCategoryTypeName = transActionCategoryTypeName
            it.transActionTypeName = transActionTypeName
        })
    }

    fun onlinePaymentBills(
        bills: List<String>,
        product: String,
        activity: WhyGoogleActivity<*>,
        changeStatus: OnChangeStatus
    ) {
        Payment.onlinePaymentBills(bills, product, activity, changeStatus)
    }

    fun getInquiryHistory(
        product: String,
        changeStatus: OnChangeStatus,
        callBack: (List<ir.ayantech.pishkhancore.model.InquiryHistory>?) -> Unit
    ) {
        InquiryHistory.getInquiryHistoryList(product, changeStatus) { list ->
            callBack.invoke(list)
        }
    }
}