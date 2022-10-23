package ir.ayantech.pishkhancore.model

data class UserSubscriptionGetInfoInput(
    //TODO application name
    val DeviceType: String,
    //TODO version name
    val DeviceVersion: String,
    //TODO application category
    val ReferenceToken: String,
    //TODO application category
    val ReferenceTokenTypeName: String
)