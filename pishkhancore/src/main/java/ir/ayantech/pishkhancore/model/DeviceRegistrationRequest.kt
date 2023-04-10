package ir.ayantech.pishkhancore.model

data class DeviceRegistrationRequestInput(
    val MobileNumber: String
)

data class DeviceRegistrationRequestOutput(
    val CountDown: Long
)