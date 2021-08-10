package ir.ayantech.pishkhancore.model

data class BillsPaymentGetLinkInput(
    val Bills: List<String>,
    val GatewayID: Long,

)

data class BillsPaymentGetLinkOutput(
    val PaymentKey: String,
    val PaymentLink: String
)