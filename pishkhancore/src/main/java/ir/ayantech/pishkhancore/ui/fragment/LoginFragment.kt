package ir.ayantech.pishkhancore.ui.fragment

import PishkhanUser
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.phone.SmsRetriever
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.databinding.FragmentLoginBinding
import ir.ayantech.pishkhancore.helper.SMSBroadcastReceiver
import ir.ayantech.pishkhancore.helper.startTimer
import ir.ayantech.pishkhancore.helper.textChanges
import ir.ayantech.pishkhancore.model.*
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import ir.ayantech.whygoogle.helper.changeVisibility
import ir.ayantech.whygoogle.helper.isNotNull
import ir.ayantech.whygoogle.helper.isNull
import ir.ayantech.whygoogle.helper.makeVisible

class LoginFragment: WhyGoogleFragment<FragmentLoginBinding>() {

    private var phoneNumber = ""
    private var timer: CountDownTimer? = null
    private var smsBroadcastReceiver: SMSBroadcastReceiver? = null
    private val REQUEST_USER_CONSENT = 200

    var callback: (() -> Unit)? = null
    var changeStatus: OnChangeStatus? = null
    var failure: OnFailure? = null
    @DrawableRes var productImageResource: Int? = null

    var otpCodeLength: Int = 4
    var autoConfirmAfterCodeReceived = true

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accessViews {
            productIv.changeVisibility(show = productImageResource.isNotNull())
            productImageResource?.let { productIv.setImageResource(it) }

            phoneNumberEt.textChanges {
                sendOtpBtn.isEnabled = phoneNumberEt.text.toString().length >= 10
            }

            otpCodeEt.textChanges {
                confirmOtpBtn.isEnabled = it.length == otpCodeLength
            }

            sendOtpBtn.setOnClickListener {
                phoneNumber = phoneNumberEt.text.toString()
                requestCode()
            }

            resendOtpTv.setOnClickListener {
                resendCode()
            }

            confirmOtpBtn.setOnClickListener {
                confirmCode()
            }

            editPhoneNumberTv.setOnClickListener {
                phoneNumberEt.isEnabled = true
                phoneNumberEt.requestFocus()
                phoneNumberEt.selectAll()
            }
        }
    }

    private fun requestCode() {
        accessViews {
            PishkhanCore.ayanApi?.simpleCall<DeviceRegistrationRequestOutput>(
                EndPoint.DeviceRegistrationRequest,
                DeviceRegistrationRequestInput(phoneNumber)
            ) { resp ->
                accessViews {
                    phoneNumberEt.isEnabled = false
                    sendOtpBtn.isEnabled = false
                    otpLl.makeVisible()
                    resendOtpTv.makeVisible()
                    editPhoneNumberTv.makeVisible()
                    sentCodeDescription.text = getString(R.string.enter_otp_code, phoneNumber)
                    timer?.cancel()
                    timer = startTimer(
                        millisInFuture = resp?.CountDown ?: 120000,
                        onTick = { millisUntilFinished ->
                            val minutes = (millisUntilFinished / 1000) / 60
                            val seconds = (millisUntilFinished / 1000) % 60
                            val time = "$minutes:$seconds"
                            resendOtpTv.text = getString(R.string.resend_otp_code, time)
                        },
                        onFinish = {
                            resendOtpTv.isEnabled = true
                        }
                    )
                }
            }
        }
    }

    private fun resendCode() {
        requestCode()
    }

    private fun confirmCode() {
        accessViews {
            PishkhanCore.ayanApi?.ayanCall<Unit>(
                endPoint = EndPoint.DeviceRegistrationConfirm,
                input = DeviceRegistrationConfirmInput(phoneNumber, otpCodeEt.text.toString()),
                ayanCallStatus = AyanCallStatus {
                    success {
                        PishkhanUser.savePhoneNumber(context = requireActivity(), phoneNumber = phoneNumber)
                        callback?.invoke()
                    }

                    failure?.let { failure(it) }
                    changeStatus?.let { changeStatus(it) }
                }
            )
        }
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }

    override fun onStart() {
        super.onStart()

        registerBroadcastReceiver()
    }

    private fun registerBroadcastReceiver() {

        smsBroadcastReceiver = SMSBroadcastReceiver()
        smsBroadcastReceiver?.otpListener = object : SMSBroadcastReceiver.SmsBroadcastReceiverListener {

            override fun onOTPReceived(intent: Intent?) {
                receiverLauncher.launch(intent)
            }

            override fun onTimeOut(message: String) {
                Log.d("OTP", "onRegisterReceiver: $message")
            }
        }

        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)

        requireActivity().registerReceiver(smsBroadcastReceiver, intentFilter)
    }

    private val receiverLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == AppCompatActivity.RESULT_OK) {
            val message = activityResult.data?.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
            val firstLine = message?.split("\n")?.firstOrNull()
            val code = firstLine?.split(":")?.get(1)?.trim()
            binding.otpCodeEt.setText(code)
            if (autoConfirmAfterCodeReceived)
                confirmCode()
        }
    }

    override fun onDestroy() {
        smsBroadcastReceiver?.let {
            requireActivity().unregisterReceiver(it)
        }
        super.onDestroy()
    }
}