package ir.ayantech.pishkhansample.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.model.UserSubscriptionGetInfoInput
import ir.ayantech.pishkhansample.R
import ir.ayantech.pishkhansample.databinding.ActivityMainBinding
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.SimpleCallBack

class MainActivity : WhyGoogleActivity<ActivityMainBinding>() {
    var bsheet: BottomSheetDialogFragment? = null

    val VERSION_NAME = "19.0.0"

    val applicationName = "PishkhanTrafficFinesInquiry"

    val applicationType = "android"

    val category = "cafebazaar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PishkhanCore.loginPishkhanWithUpdatedToken(
            activity = this,
            userSubscriptionGetInfoInput = UserSubscriptionGetInfoInput(
                DeviceType = applicationName,
                DeviceVersion = VERSION_NAME,
                ReferenceToken = category,
                ReferenceTokenTypeName = category
            ),
            changeStatus = {},
            failure = {
                Toast.makeText(this, it.failureMessage, Toast.LENGTH_SHORT).show()
            },
            forceLogin = true,
            productImageResource = R.drawable.ic_technicalexaminationcertificate,
            initAdvertisement = {
                PishkhanCore.startLoginFragment(
                    activity = this,
                    changeStatus = {},
                    failure = {
                        Toast.makeText(this, it.failureMessage, Toast.LENGTH_SHORT).show()
                    },
                    productImageResource = R.drawable.ic_technicalexaminationcertificate,
                ) {
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                }
            }
        )



//        login {
//            Toast.makeText(this, PishkhanCore.getUserToken(this), Toast.LENGTH_SHORT).show()
//            PishkhanCore.startHistoryFragment(this, AyanHistoryFragment(), changeStatus = {
//
//            }, failure = {
//
//            }) { transaction ->
//                start(DetailFragment().also {
//                    it.transaction = transaction
//                })
//            }

//            PishkhanCore.onlinePaymentBills(listOf(), "", this, failure = {}, changeStatus = {})

//            start(InquiryHistoryFragment())
//        }
    }

    private fun login(callback: SimpleCallBack? = null) {
        bsheet = PishkhanCore.startPishkhanLogin(this) { success ->
            if (success) {
                callback?.invoke()
            } else {
                finish()
            }
        }
        bsheet?.show(supportFragmentManager, "")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (bsheet != null && (bsheet as BottomSheetDialog).isShowing) {
            ((bsheet as BottomSheetDialog)).dismiss()
        }
    }

    override val binder: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override val containerId = R.id.fragmentContainerFl

    override fun onBackPressed() {
        when {
            getFragmentCount() > 1 -> {
                getTopFragment()?.onBackPressed()
            }
            else -> {
                ActivityCompat.finishAfterTransition(this)
            }
        }
    }
}