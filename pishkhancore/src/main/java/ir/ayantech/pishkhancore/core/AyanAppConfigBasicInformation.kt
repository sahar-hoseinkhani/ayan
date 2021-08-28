package ir.ayantech.pishkhancore.core

import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.model.AppConfigBasicInformationOutput
import ir.ayantech.pishkhancore.model.EndPoint

object AyanAppConfigBasicInformation {

    fun getAppConfigBasicInformation(
        changeStatus: OnChangeStatus,
        failure: OnFailure,
        callBack: (AppConfigBasicInformationOutput) -> Unit
    ) {
        PishkhanCore.ayanApi?.ayanCall<AppConfigBasicInformationOutput>(
            AyanCallStatus {
                success {
                    it.response?.Parameters?.let { resp ->
                        callBack.invoke(resp)
                    }
                }
                changeStatus(changeStatus)
                failure(failure)
            },
            EndPoint.AppConfigBasicInformation
        )
    }
}