package ir.ayantech.pishkhancore.model

data class LoginInput(
    val AdditionalData: String? = null,
    val ApplicationName: String,
    val Channel: String? = null,
    val MobileNumber: String? = null,
    val ReferenceToken: String? = null
)

data class LoginOutput(
    val Token: String
)