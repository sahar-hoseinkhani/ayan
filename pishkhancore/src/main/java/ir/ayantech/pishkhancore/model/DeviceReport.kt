package ir.ayantech.pishkhancore.model

data class DeviceReportInput(
    //TODO application name
    val DeviceType: String,
    //TODO version name
    val DeviceVersion: String,
    //TODO application category
    val ReferenceToken: String,
    //TODO application category
    val ReferenceTokenTypeName: String
)

data class DeviceReportOutput(
    val Token: String
)