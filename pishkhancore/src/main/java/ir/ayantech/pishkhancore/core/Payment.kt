package ir.ayantech.pishkhancore.core

import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.LongCallBack
import ir.ayantech.whygoogle.helper.openUrl

object Payment {

    fun onlinePaymentBills(
        bills: List<String>,
        product: String,
        activity: WhyGoogleActivity<*>,
        changeStatus: OnChangeStatus
    ) {
        getBillIPGs(bills, product, changeStatus) { ipgId ->
            PishkhanCore.ayanApi?.simpleCall<BillsPaymentGetLinkOutput>(
                EndPoint.BillsPaymentGetLink,
                BillsPaymentGetLinkInput(bills, ipgId)
            ) {
                it?.PaymentLink?.openUrl(activity)
            }
        }
    }

    private fun getBillIPGs(
        bills: List<String>, product: String,
        changeStatus: OnChangeStatus, callback: LongCallBack
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
            }, EndPoint.BillsGetPaymentGatewayList,
            BillsGetPaymentGatewayListInput("", product, bills.size.toLong())
        )
    }
}