package ir.ayantech.pishkhancore.core

import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.model.AppConfigAdvertisementOutput
import ir.ayantech.pishkhancore.model.EndPoint
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanGeneralErrorBottomSheet
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import java.io.Serializable

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
                failure {failure ->
                    AyanGeneralErrorBottomSheet().also {
                        it.title = activity.resources.getString(R.string.error)
                        it.message = failure.failureMessage
                        it.buttonText = activity.resources.getString(R.string.retry)
                        it.retry = failure.reCallApi as Serializable
                    }.show(activity.supportFragmentManager, "AyanGeneralErrorBottomSheet")
                }
            },
            EndPoint.AppConfigAdvertisement
        )
    }
}