package ir.ayantech.pishkhancore.core

import PishkhanUser
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.AyanCommonCallStatus
import ir.ayantech.ayannetworking.api.WrappedPackage
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.helper.InformationHelper
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.pishkhancore.ui.dialog.AyanVersionControlDialog

open class VersionControl(
    private val activity: AppCompatActivity,
    private val applicationUniqueToken: String,
    private val ayanCommonCallStatus: AyanCommonCallStatus
) {
    var checkVersion: WrappedPackage<*, CheckVersionOutput>? = null
    var getLastVersion: WrappedPackage<*, GetLastVersionOutput>? = null

    fun checkForNewVersion(
        callback: (updateStatus: Boolean) -> Unit
    ) {
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
                AppExtraInfo(PishkhanUser.session)
            ), commonCallStatus = ayanCommonCallStatus,
            baseUrl = PishkhanCore.versionControllingBaseUrl!!,
            hasIdentity = false
        )
    }

    private fun getLastVersion(
        callback: (updateStatus: Boolean) -> Unit
    ) {
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
                AppExtraInfo(PishkhanUser.session)
            ), commonCallStatus = ayanCommonCallStatus,
            baseUrl = PishkhanCore.versionControllingBaseUrl!!,
            hasIdentity = false
        )
    }

    companion object {

//        const val defaultBaseUrl = "https://versioncontrol.infra.ayantech.ir/WebServices/App.svc/"


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
                    AppExtraInfo(PishkhanUser.session)
                ), commonCallStatus = AyanCommonCallStatus {
                    failure {
                        Toast.makeText(
                            context,
                            context.resources.getString(R.string.please_check_your_internet_connection_try_again),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, baseUrl = PishkhanCore.versionControllingBaseUrl!!,
                hasIdentity = false
            )

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
                    AppExtraInfo(PishkhanUser.session)
                ), commonCallStatus = AyanCommonCallStatus {
                    failure {
                        Toast.makeText(
                            context,
                            context.resources.getString(R.string.please_check_your_internet_connection_try_again),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, baseUrl = PishkhanCore.versionControllingBaseUrl!!,
                hasIdentity = false
            )
        }

        private fun share(context: Context, shareBody: String) {
            ShareCompat.IntentBuilder.from(context as AppCompatActivity)
                .setText(shareBody)
                .setType("text/plain")
                .setChooserTitle(context.resources.getString(R.string.share_via))
                .startChooser()
        }
    }
}