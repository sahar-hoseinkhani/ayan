package ir.ayantech.pishkhancore.core

import android.content.Context
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.model.AppConfigAdvertisementOutput
import ir.ayantech.pishkhancore.model.EndPoint
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanErrorBottomSheet

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
                    AyanErrorBottomSheet(context, it.failureMessage) {
                        it.reCallApi()
                    }.show()
                }
            },
            EndPoint.AppConfigAdvertisement
        )
    }
}