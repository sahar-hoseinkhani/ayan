package ir.ayantech.pishkhancore.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SMSBroadcastReceiver : BroadcastReceiver() {

    var otpListener: SmsBroadcastReceiverListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
            val extras = intent.extras
            val status = extras?.get(SmsRetriever.EXTRA_STATUS) as? Status?
            when (status?.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    if (otpListener != null) {
                        val i = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                        otpListener?.onOTPReceived(i)
                    }
                }

                CommonStatusCodes.TIMEOUT -> {
                    if (otpListener != null) {
                        otpListener?.onTimeOut("timeout")
                    }
                }
            }
        }
    }
//    SmsRetriever provides access to Google services that help you retrieve SMS messages
//    sent to your app without having to ask for android.permission.READ_SMS or android.permission.RECEIVE_SMS.
//    To use SmsRetriever, obtain an instance of SmsRetrieverClient using getClient(Context) or getClient(Activity),
//    then start the SMS retriever service by calling SmsRetrieverClient.startSmsRetriever()  or
//    SmsRetrieverClient.startSmsUserConsent(String). The service waits for a matching SMS

    interface SmsBroadcastReceiverListener {

        fun onOTPReceived(intent: Intent?)

        fun onTimeOut(message: String)
    }
}