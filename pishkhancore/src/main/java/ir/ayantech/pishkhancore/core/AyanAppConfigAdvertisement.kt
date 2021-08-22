package ir.ayantech.pishkhancore.core

import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.model.AppConfigAdvertisementOutput
import ir.ayantech.pishkhancore.model.EndPoint

object AyanAppConfigAdvertisement {

    fun getAppConfigAdvertisement(
        failure: OnFailure,
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
                failure(failure)
            },
            EndPoint.AppConfigAdvertisement
        )
    }
}