package ir.ayantech.pishkhancore.ui.fragment

import PishkhanUser
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayannetworking.api.OnChangeStatus
import ir.ayantech.ayannetworking.api.OnFailure
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.databinding.FragmentLoginBinding
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

    var callback: (() -> Unit)? = null
    var changeStatus: OnChangeStatus? = null
    var failure: OnFailure? = null
    @DrawableRes var productImageResource: Int? = null

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
                if (it.length == 4)
                    confirmCode()
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
                phoneNumberLl.isEnabled = true
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
                    phoneNumberLl.isEnabled = false
                    otpLl.makeVisible()
                    resendOtpTv.makeVisible()
                    editPhoneNumberTv.makeVisible()
                    sentCodeDescription.text = getString(R.string.enter_otp_code, phoneNumber)
                    startTimer(
                        millisInFuture = resp?.CountDown ?: 120000,
                        onTick = { millisUntilFinished ->
                            val minutes = (millisUntilFinished / 1000) / 60
                            val seconds = (millisUntilFinished / 1000) % 60
                            val time = "$minutes:$seconds"
                            binding.resendOtpTv.text = getString(R.string.resend_otp_code, time)
                        },
                        onFinish = {
                            binding.resendOtpTv.isEnabled = true
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
}