package ir.ayantech.pishkhancore.core

import android.content.Context
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.whygoogle.helper.LongCallBack
import ir.ayantech.whygoogle.helper.openUrl
import kotlin.Result.Companion.success

object AyanPayment {

    fun onlinePaymentBills(
        bills: List<String>,
        product: String,
        mobilePhone: String?,
        context: Context,
        changeStatus: OnChangeStatus,
        failure: OnFailure
    ) {
        getBillIPGs(bills, product, changeStatus, failure) { ipgId ->
            PishkhanCore.ayanApi?.ayanCall<BillsPaymentGetLinkOutput>(
                AyanCallStatus {
                    success {
                        it.response?.Parameters?.let { output ->
                            output.PaymentLink.openUrl(context)
                        }
                    }
                    changeStatus(changeStatus)
                    failure(failure)
                }, EndPoint.BillsPaymentGetLink,
                BillsPaymentGetLinkInput(bills, ipgId, mobilePhone)
            )
        }
    }

    private fun getBillIPGs(
        bills: List<String>, product: String,
        changeStatus: OnChangeStatus, failure: OnFailure, callback: LongCallBack
    ) {
        PishkhanCore.ayanApi?.ayanCall<SalesGetPaymentGatewayListOutput>(
            AyanCallStatus {
                success {
                    it.response?.Parameters?.PaymentGatewayList?.let {
                        it.firstOrNull { it.Default }?.ID?.let { it1 ->
                            callback(it1)
                        }
                    }
                }
                changeStatus(changeStatus)
                failure(failure)
            }, EndPoint.BillsGetPaymentGatewayList,
            BillsGetPaymentGatewayListInput("", product, bills.size.toLong())
        )
    }
}