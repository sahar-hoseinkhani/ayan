package ir.ayantech.pishkhancore.model

data class SalesGetPaymentGatewayListInput(
    val SaleKey: String
)

data class SalesGetPaymentGatewayListOutput(
    val PaymentGatewayList: List<PaymentGateway>,
    val SelectOption: Boolean,
    val Title: String
)

data class PaymentGateway(
    val Default: Boolean,
    val ID: Long,
    val ShowName: String,
    val Icon: String,
    var selected: Boolean = false
)