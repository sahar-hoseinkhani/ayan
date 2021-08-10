package ir.ayantech.pishkhancore.model

data class BillsGetPaymentGatewayListInput(
    val QueueUniqueID: String,
    val InquiryTypeName: String?,
    val BillCount: Long
)