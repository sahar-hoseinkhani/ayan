package ir.ayantech.pishkhancore.model

data class CheckVersionInput(
    val ApplicationName: String,
    val ApplicationType: String,
    val CategoryName: String,
    val CurrentApplicationVersion: String,
    val ExtraInfo: Any
)

data class CheckVersionOutput(
    val UpdateStatus: String
)