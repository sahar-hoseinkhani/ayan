package ir.ayantech.pishkhancore.core

import android.content.Context
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.model.AppConfigAdvertisementOutput
import ir.ayantech.pishkhancore.model.EndPoint
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanGeneralErrorBottomSheet

object AyanAppConfigAdvertisement {

    fun getAppConfigAdvertisement(
        context: Context,
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
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = it.failureMessage,
                        buttonText = context.resources.getString(R.string.retry)
                    ) {
                        it.reCallApi()
                    }.show()
                }
            },
            EndPoint.AppConfigAdvertisement
        )
    }
}