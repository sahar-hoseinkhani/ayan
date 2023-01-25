package ir.ayantech.pishkhancore.core

import ir.ayantech.pishkhancore.model.EndPoint
import ir.ayantech.pishkhancore.model.post_package.InquiryPostPackageTrackingInput
import ir.ayantech.pishkhancore.model.post_package.InquiryPostPackageTrackingOutput
import ir.ayantech.pishkhancore.ui.fragment.PostTrackingResultFragment
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import kotlinx.coroutines.NonCancellable.start

fun getPassportStatusFromPost(
    packageNumber: String,
    navigateToPostResult: (InquiryPostPackageTrackingOutput?) -> Unit
) {
    PishkhanCore.ayanApi?.simpleCall<InquiryPostPackageTrackingOutput>(
        endPoint = EndPoint.InquiryPostPackageTracking,
        input = InquiryPostPackageTrackingInput(PackageNumber = packageNumber),
        onSuccess = navigateToPostResult
    )
}
