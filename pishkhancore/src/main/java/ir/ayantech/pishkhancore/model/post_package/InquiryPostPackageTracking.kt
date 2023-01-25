package ir.ayantech.pishkhancore.model.post_package

import ir.ayantech.pishkhancore.helper.getKeyValueExtraInfo
import ir.ayantech.pishkhancore.model.Advertisement
import ir.ayantech.pishkhancore.model.DateTime
import ir.ayantech.pishkhancore.model.InquiryHistory
import ir.ayantech.pishkhancore.model.KeyValue

data class InquiryPostPackageTrackingInput(
    val PackageNumber: String
)

data class InquiryPostPackageTrackingOutput(
    val Advertisement: Advertisement,
    val Inquiry: InquiryHistory,
    val Result: PostResult
)

data class PostResult(
    val AcceptanceDateTime: DateTime,
    val Destination: String,
    val Events: List<PostEvent>?,
    val ExtraInfo: String,
    val InsuranceCost: String,
    val PackageNumber: String,
    val PostCost: String,
    val ReceiverName: String,
    val ReceiverZip: String,
    val SenderName: String,
    val SenderZip: String,
    val ServiceType: String,
    val Source: String,
    val SourcePostOffice: String,
    val Weight: String
)

data class PostEvent(
    val DateTime: DateTime,
    val EventNumber: String,
    val ExtraInfo: String?,
    val Province: String
)

fun PostEvent.getEventShowKeyValue(dateTime: String, place: String): List<KeyValue> {
    val result = arrayListOf<KeyValue>()
    result.add(KeyValue(dateTime, DateTime.Persian.DateFormatted))
    result.add(KeyValue(place, Province))
    ExtraInfo?.getKeyValueExtraInfo()?.let { result.addAll(it) }
    return result
}
