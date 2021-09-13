package ir.ayantech.pishkhancore.model

data class InquiryBookmarkItemInput(
    val Favorite: Boolean,
    val NotificationPermission: Boolean,
    val InquiryTypeName: String,
    val Note: String,
    val UniqueID: String,
    val Action: String? = null
) {
    companion object {
        const val NotificationPermissionAction = "NotificationPermission"
    }
}