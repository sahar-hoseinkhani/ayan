package ir.ayantech.pishkhancore.core

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.core.app.ShareCompat
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.AyanCommonCallStatus
import ir.ayantech.ayannetworking.api.WrappedPackage
import ir.ayantech.pishkhancore.helper.InformationHelper
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.pishkhancore.ui.dialog.AyanVersionControlDialog
import ir.ayantech.whygoogle.activity.WhyGoogleActivity

internal class VersionControl(
    private val activity: WhyGoogleActivity<*>,
    private val applicationUniqueToken: String,
    private val ayanCommonCallStatus: AyanCommonCallStatus
) {
    var checkVersion: WrappedPackage<*, CheckVersionOutput>? = null
    var getLastVersion: WrappedPackage<*, GetLastVersionOutput>? = null

    fun checkForNewVersion(callback: (updateStatus: Boolean) -> Unit) {
        checkVersion = PishkhanCore.ayanApi?.ayanCall(
            AyanCallStatus {
                success {
                    if (it.response?.Parameters?.UpdateStatus == VersionControlUpdateStatus.NOT_REQUIRED) {
                        callback(true)
                        return@success
                    }
                    getLastVersion(callback)
                }
            },
            EndPoint.CheckVersion,
            CheckVersionInput(
                InformationHelper.getApplicationName(activity),
                InformationHelper.getApplicationType(activity),
                InformationHelper.getApplicationCategory(applicationUniqueToken),
                InformationHelper.getApplicationVersion(activity),
                AppExtraInfo(PishkhanUser.getSession(activity))
            ), commonCallStatus = ayanCommonCallStatus,
            baseUrl = defaultBaseUrl,
            hasIdentity = false
        )
    }

    private fun getLastVersion(callback: (updateStatus: Boolean) -> Unit) {
        getLastVersion = PishkhanCore.ayanApi?.ayanCall(
            AyanCallStatus {
                success {
                    AyanVersionControlDialog(
                        activity,
                        checkVersion?.response?.Parameters!!,
                        getLastVersion?.response?.Parameters!!,
                        callback
                    ).show()
                }
            },
            EndPoint.GetLastVersion,
            GetLastVersionInput(
                InformationHelper.getApplicationName(activity),
                InformationHelper.getApplicationType(activity),
                InformationHelper.getApplicationCategory(applicationUniqueToken),
                InformationHelper.getApplicationVersion(activity),
                AppExtraInfo(PishkhanUser.getSession(activity))
            ), commonCallStatus = ayanCommonCallStatus,
            baseUrl = defaultBaseUrl,
            hasIdentity = false
        )
    }

    companion object {

        const val defaultBaseUrl = "https://versioncontrol.infra.ayantech.ir/WebServices/App.svc/"

        fun shareApp(context: Context, applicationUniqueToken: String) {
            PishkhanCore.ayanApi?.ayanCall<GetLastVersionOutput>(
                AyanCallStatus {
                    success {
                        share(context, it.response?.Parameters?.TextToShare!!)
                    }
                },
                EndPoint.GetLastVersion,
                GetLastVersionInput(
                    InformationHelper.getApplicationName(context),
                    InformationHelper.getApplicationType(context),
                    InformationHelper.getApplicationCategory(applicationUniqueToken),
                    InformationHelper.getApplicationVersion(context),
                    AppExtraInfo(PishkhanUser.getSession(context))
                ), commonCallStatus = AyanCommonCallStatus {
                    failure {
                        Toast.makeText(
                            context,
                            "لطفا اتصال اینترنت خود را بررسی کرده و دوباره تلاش نمایید.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, hasIdentity = false)

        }

        fun getDownloadLink(
            context: Context,
            applicationUniqueToken: String,
            callback: (downloadLink: String) -> Unit
        ) {
            PishkhanCore.ayanApi?.ayanCall<GetLastVersionOutput>(
                AyanCallStatus {
                    success {
                        callback(it.response?.Parameters?.Link ?: "")
                    }
                },
                EndPoint.GetLastVersion,
                GetLastVersionInput(
                    InformationHelper.getApplicationName(context),
                    InformationHelper.getApplicationType(context),
                    InformationHelper.getApplicationCategory(applicationUniqueToken),
                    InformationHelper.getApplicationVersion(context),
                    AppExtraInfo(PishkhanUser.getSession(context))
                ), commonCallStatus = AyanCommonCallStatus {
                    failure {
                        Toast.makeText(
                            context,
                            "لطفا اتصال اینترنت خود را بررسی کرده و دوباره تلاش نمایید.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, baseUrl = defaultBaseUrl,
                hasIdentity = false
            )
        }

        private fun share(context: Context, shareBody: String) {
            ShareCompat.IntentBuilder.from(context as Activity)
                .setText(shareBody)
                .setType("text/plain")
                .setChooserTitle("به اشتراک گذاری از طریق:")
                .startChooser()
        }
    }
}