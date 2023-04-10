package ir.ayantech.pishkhancore.model

object EndPoint {
    const val CheckVersion = "CheckVersion"
    const val GetLastVersion = "GetLastVersion"
    const val Login = "Login"
    const val PaymentHistoryGetTransactionList = "PaymentHistoryGetTransactionList"
    const val PaymentHistoryGetTransactionInfo = "PaymentHistoryGetTransactionInfo"
    const val BillsPaymentGetLink = "BillsPaymentGetLink"
    const val BillsGetPaymentGatewayList = "BillsGetPaymentGatewayList"
    const val InquiryGetRecentList = "InquiryGetRecentList"
    const val InquiryDeleteRecentListItem = "InquiryDeleteRecentListItem"
    const val AppConfigAdvertisement = "AppConfigAdvertisement"
    const val AppConfigBasicInformation = "AppConfigBasicInformation"
    const val InquiryBookmarkItem = "InquiryBookmarkItem"
    const val UserSubscriptionGetInfo = "UserSubscriptionGetInfo"
    const val InquiryPostPackageTracking = "InquiryPostPackageTracking"
    const val DeviceRegistrationRequest = "DeviceRegistrationRequest"    //	To Send OTP Message For Registration.
    const val DeviceRegistrationConfirm = "DeviceRegistrationConfirm"    //	To Confirm Device Registration.
    const val DeviceReport = "DeviceReport"
}