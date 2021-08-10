package ir.ayantech.pishkhancore.core

import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.pishkhancore.model.EndPoint
import ir.ayantech.pishkhancore.model.InquiryGetRecentListInput
import ir.ayantech.pishkhancore.model.InquiryGetRecentListOutput
import ir.ayantech.pishkhancore.model.InquiryHistory

object InquiryHistory {

    fun getInquiryHistoryList(
        product: String,
        changeStatus: OnChangeStatus,
        callBack: (List<InquiryHistory>?) -> Unit
    ) {
        PishkhanCore.ayanApi?.ayanCall<InquiryGetRecentListOutput>(
            AyanCallStatus {
                success {
                    it.response?.Parameters?.Recent?.let { inquiryHistoryList ->
                        callBack.invoke(inquiryHistoryList)
                    }
                }
                changeStatus(changeStatus)
                failure {
                    callBack.invoke(null)
                }
            },
            EndPoint.InquiryGetRecentList,
            InquiryGetRecentListInput(product)
        )
    }
}