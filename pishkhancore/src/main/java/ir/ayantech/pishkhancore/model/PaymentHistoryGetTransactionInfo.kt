package ir.ayantech.pishkhancore.model

data class PaymentHistoryGetTransactionInfoInput(
    val UniqueID: String
)

data class PaymentHistoryGetTransactionInfoOutput(
    val Amount: Long,
    val Buttons: List<MessageButton>?,
    val DateTime: DateTime,
    val Bills: String?,
    val Description: String?,
    val ExtraInfo: List<KeyValue>?,
    val Details: ArrayList<ArrayList<KeyValue>>?,
    val Inquiry: InquiryHistory?,
    val ReceiptUrl: String?,
    val TraceNumberReviewLink: String?,
    val Status: NameShowName,
    val TextToShare: String,
    val Icon: NameShowName?,
    val Title: String,
    val Type: Type,
    val UniqueID: String
)

data class MessageButton(
    val Event: String?,
    val GradientColorEnd: String,
    val GradientColorStart: String,
    val Icon: String?,
    val Link: String,
    val Title: String
)

data class KeyValue(
    val Key: String,
    var Value: String?,
    var canCopy: Boolean = false,
    var textColor: Int = 0
)