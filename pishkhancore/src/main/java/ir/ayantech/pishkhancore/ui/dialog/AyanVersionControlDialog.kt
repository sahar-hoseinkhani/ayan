package ir.ayantech.pishkhancore.ui.dialog

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.FileProvider
import com.coolerfall.download.*
import ir.ayantech.pishkhancore.databinding.DialogAyanVerionControlBinding
import ir.ayantech.pishkhancore.model.CheckVersionOutput
import ir.ayantech.pishkhancore.model.GetLastVersionOutput
import ir.ayantech.pishkhancore.model.VersionControlLinkType
import ir.ayantech.pishkhancore.model.VersionControlUpdateStatus
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.makeVisible
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

class AyanVersionControlDialog(
    activity: WhyGoogleActivity<*>,
    private val checkVersion: CheckVersionOutput,
    private val getLastVersion: GetLastVersionOutput,
    private val callback: (updateStatus: Boolean) -> Unit
) : AyanBaseDialog<DialogAyanVerionControlBinding>(activity) {

    private var id = -1
    private var manager: DownloadManager? = null

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupActions()
        binding.apply {
            titleTv.text = getLastVersion.Title
            messageTv.text = getLastVersion.Body
            positiveTv.text = getLastVersion.AcceptButtonText
            negativeTv.text = getLastVersion.RejectButtonText
            when {
                getLastVersion.ChangeLogs == null -> changeLogTv.visibility = View.GONE
                getLastVersion.ChangeLogs.isEmpty() -> changeLogTv.visibility = View.GONE
                else -> {
                    val changeLog = StringBuilder()
                    for (s in getLastVersion.ChangeLogs) {
                        changeLog.append(s).append("\n")
                    }
                    changeLogTv.text = changeLog
                }
            }
        }
    }

    private fun setupActions() {
        binding.apply {
            positiveTv.setOnClickListener {
                try {
                    if (getLastVersion.LinkType == VersionControlLinkType.DIRECT) {
                        if (getRootDirPath(context) == null) {
                            openUrl(context, getLastVersion.Link)
                            callback(false)
                            return@setOnClickListener
                        }
                        progressBar.makeVisible()
                        progressTv.makeVisible()
                        val destPath =
                            getRootDirPath(context) + "/newversion" + Date().time.toString() + ".apk"
                        val request = DownloadRequest.Builder()
                            .url(getLastVersion.Link)
                            .retryTime(5)
                            .retryInterval(2, TimeUnit.SECONDS)
                            .progressInterval(100, TimeUnit.MILLISECONDS)
                            .priority(Priority.HIGH)
                            .destinationFilePath(destPath)
                            .downloadCallback(object : DownloadCallback() {
                                override fun onStart(downloadId: Int, totalBytes: Long) {}

                                override fun onRetry(downloadId: Int) {}

                                override fun onProgress(
                                    downloadId: Int,
                                    bytesWritten: Long,
                                    totalBytes: Long
                                ) {
                                    val progressPercent = bytesWritten * 100 / totalBytes
                                    progressBar.progress = progressPercent.toInt()
                                    progressTv.text =
                                        String.format("%%%s", progressPercent.toString())
                                }

                                override fun onSuccess(downloadId: Int, filePath: String?) {
                                    try {
                                        installApp(context, filePath)
                                        dismiss()
                                        callback(false)
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }

                                }

                                override fun onFailure(
                                    downloadId: Int,
                                    statusCode: Int,
                                    errMsg: String?
                                ) {
                                    Log.e("AyanVC:", errMsg ?: "")
                                    dismiss()
                                    openUrl(context, getLastVersion.Link)
                                    callback(false)
                                }
                            })
                            .build()
                        manager?.let {
                            id = it.add(request)
                        }
                    } else if (getLastVersion.LinkType == VersionControlLinkType.PAGE) {
                        dismiss()
                        openUrl(context, getLastVersion.Link)
                        callback(false)
                    }
                } catch (e: Exception) {
                    dismiss()
                    openUrl(context, getLastVersion.Link)
                    callback(false)
                }
            }
            negativeTv.setOnClickListener {
                try {
                    manager?.cancel(id)
                } catch (e: Exception) {
                }

                dismiss()
                callback(checkVersion.UpdateStatus != VersionControlUpdateStatus.MANDATORY)
            }
        }
    }

    private fun installApp(context: Context, path: String?) {
        val toInstall = File(path!!)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                val apkUri = FileProvider.getUriForFile(
                    context,
                    context.packageName + ".provider",
                    toInstall
                )
                val intent = Intent(Intent.ACTION_INSTALL_PACKAGE)
                intent.data = apkUri
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.startActivity(intent)
            } catch (e1: Exception) {
                e1.printStackTrace()
            }

        } else {
            val apkUri = Uri.fromFile(toInstall)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.startActivity(intent)
        }
    }

    private fun openUrl(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

    private fun getRootDirPath(context: Context): String? {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> context.filesDir.absolutePath
            Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() -> context.getExternalFilesDir(
                null
            )!!.absolutePath
            else -> null
        }
    }

    override val binder: (LayoutInflater) -> DialogAyanVerionControlBinding
        get() = DialogAyanVerionControlBinding::inflate
}