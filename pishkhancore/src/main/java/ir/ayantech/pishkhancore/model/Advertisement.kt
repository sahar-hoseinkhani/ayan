package ir.ayantech.pishkhancore.model

data class Advertisement(
    val Dialog: Dialog?,
    val ShowDialog: Boolean,
    val TimeOut: Long,
    val UniqueID: String
)

data class Dialog(
    val BannerRedirectUrl: String?,
    val BannerUrl: String?,
    val DataSources: String?,
    val ButtonTextShowDetails: String?,
    val ButtonTextSubscriptionPurchase: String,
    val Message: String,
    val Summary: Summary?
)

data class Summary(
    val ExtraInfo: List<KeyValue>,
    val PayableAmount: Long,
    val PayableBills: List<String>?
)

object DataSources {
    const val TapsellRewardedVideo = "TapsellRewardedVideo"
    const val TapsellInterstitial = "TapsellInterstitial"
    const val InternalInterstitial = "InternalInterstitial"
    const val AdMobRewardedVideo = "AdMobRewardedVideo"
    const val AdMobInterstitial = "AdMobInterstitial"
}