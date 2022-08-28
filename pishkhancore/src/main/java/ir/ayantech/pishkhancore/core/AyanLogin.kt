package ir.ayantech.pishkhancore.core

import androidx.appcompat.app.AppCompatActivity
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.helper.InformationHelper
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.whygoogle.helper.BooleanCallBack

object AyanLogin {

    fun login(
        activity: AppCompatActivity,
        additionalData: String?,
        mobileNumber: String?,
        referenceToken: String?,
        changeStatus: OnChangeStatus,
        failure: OnFailure,
        callBack: BooleanCallBack
    ) {
        PishkhanCore.ayanApi?.ayanCall<LoginOutput>(
            AyanCallStatus {
                success {
                    it.response?.Parameters?.let {
                        PishkhanUser.saveSession(activity, it.Token)
                        callBack.invoke(true)
                    }
                }
                changeStatus(changeStatus)
                failure(failure)
            }, EndPoint.Login,
            LoginInput(
                additionalData,
                ApplicationName = InformationHelper.getApplicationNameForPishkhan(activity),
                Channel = PishkhanCore.applicationUniqueToken?.let {
                    InformationHelper.getApplicationCategory(
                        it
                    )
                },
                mobileNumber,
                referenceToken,
                InformationHelper.getApplicationVersion(activity)
            ),
            baseUrl = PishkhanCore.serviceBaseUrl!!,
            hasIdentity = false
        )
    }
}