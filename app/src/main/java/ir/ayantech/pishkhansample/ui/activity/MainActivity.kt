package ir.ayantech.pishkhansample.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.ui.fragment.AyanHistoryFragment
import ir.ayantech.pishkhansample.R
import ir.ayantech.pishkhansample.databinding.ActivityMainBinding
import ir.ayantech.pishkhansample.ui.fragment.DetailFragment
import ir.ayantech.pishkhansample.ui.fragment.InquiryHistoryFragment
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.SimpleCallBack

class MainActivity : WhyGoogleActivity<ActivityMainBinding>() {
    var bsheet: BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login {
            Toast.makeText(this, PishkhanCore.getUserToken(this), Toast.LENGTH_SHORT).show()
            PishkhanCore.startHistoryFragment(this, AyanHistoryFragment(), changeStatus = {

            }, failure = {

            }) { transaction ->
                start(DetailFragment().also {
                    it.transaction = transaction
                })
            }

//            PishkhanCore.onlinePaymentBills(listOf(), "", this, failure = {}, changeStatus = {})

//            start(InquiryHistoryFragment())
        }
    }

    private fun login(callback: SimpleCallBack? = null) {
        bsheet = PishkhanCore.startPishkhanLogin(this) { success ->
            if (success) {
                callback?.invoke()
            } else {
                finish()
            }
        }
        bsheet?.show()
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