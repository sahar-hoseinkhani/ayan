package ir.ayantech.pishkhancore.core

import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.model.AppConfigAdvertisementOutput
import ir.ayantech.pishkhancore.model.EndPoint
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanGeneralErrorBottomSheet
import ir.ayantech.whygoogle.activity.WhyGoogleActivity

object AyanAppConfigAdvertisement {

    fun getAppConfigAdvertisement(
        activity: WhyGoogleActivity<*>,
        callBack: (AppConfigAdvertisementOutput) -> Unit
    ) {
        PishkhanCore.ayanApi?.ayanCall<AppConfigAdvertisementOutput>(
            AyanCallStatus {
                success {
                    it.response?.Parameters?.let { resp ->
                        callBack.invoke(resp)
                    }
                }
                changeStatus { }
                failure {
                    AyanGeneralErrorBottomSheet(
                        title = activity.resources.getString(R.string.error),
                        message = it.failureMessage,
                        buttonText = activity.resources.getString(R.string.retry)
                    ) {
                        it.reCallApi()
                    }.show(activity.supportFragmentManager, "AyanGeneralErrorBottomSheet")
                }
            },
            EndPoint.AppConfigAdvertisement
        )
    }
}