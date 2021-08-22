package ir.ayantech.pishkhancore.core

import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.ayannetworking.api.SimpleCallback
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.pishkhancore.model.InquiryHistory

object AyanInquiryHistory {

    fun getInquiryHistoryList(
        product: String? = null,
        changeStatus: OnChangeStatus,
        failure: OnFailure,
        callBack: (List<InquiryHistory>?) -> Unit
    ) {
        PishkhanCore.ayanApi?.ayanCall<InquiryGetRecentListOutput>(
            AyanCallStatus {
                success {
                    it.response?.Parameters?.let { resp ->
                        callBack.invoke(resp.Recent)
                    }
                }
                changeStatus(changeStatus)
                failure(failure)
            },
            EndPoint.InquiryGetRecentList,
            InquiryGetRecentListInput(product)
        )
    }

    fun removeInquiryHistoryItem(
        uniqueId: String, changeStatus: OnChangeStatus,
        failure: OnFailure, removeSuccessCallback: SimpleCallback
    ) {
        PishkhanCore.ayanApi?.ayanCall<Void>(
            AyanCallStatus {
                success {
                    removeSuccessCallback.invoke()
                }
                changeStatus(changeStatus)
                failure(failure)
            }, EndPoint.InquiryDeleteRecentListItem,
            InquiryDeleteRecentListItemInput(uniqueId)
        )
    }
}