package ir.ayantech.pishkhancore.model

data class InquiryGetRecentListInput(
    val InquiryTypeName: String?
)

data class InquiryGetRecentListOutput(
    val Recent: List<InquiryHistory>
)

data class InquiryHistory(
    val AutoFill: Boolean,
    val DateTime: DateTime,
    var Favorite: Boolean,
    var NotificationFeature: Boolean,
    var NotificationPermission: Boolean,
    val Note: String,
    val QueryValue: String,
    val Description: String?,
    val Query: List<KeyValue>,
    val Type: NameShowName,
    val UniqueID: String
)