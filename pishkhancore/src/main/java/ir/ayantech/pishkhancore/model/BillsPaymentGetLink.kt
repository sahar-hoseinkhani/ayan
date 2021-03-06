package ir.ayantech.pishkhancore.model

data class BillsPaymentGetLinkInput(
    val Bills: List<String>,
    val GatewayID: Long,
    val MobilePhone: String? = null

)

data class BillsPaymentGetLinkOutput(
    val PaymentKey: String,
    val PaymentLink: String
)