package ir.ayantech.pishkhansample.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhansample.R
import ir.ayantech.pishkhansample.databinding.ActivityMainBinding
import ir.ayantech.pishkhansample.ui.fragment.InquiryHistoryFragment
import ir.ayantech.whygoogle.activity.WhyGoogleActivity
import ir.ayantech.whygoogle.helper.SimpleCallBack

class MainActivity : WhyGoogleActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login {
            Toast.makeText(this, PishkhanCore.getUserToken(this), Toast.LENGTH_SHORT).show()
//            PishkhanCore.startHistoryFragment(this, AyanHistoryFragment()) { transaction ->
//                start(DetailFragment().also {
//                    it.transaction = transaction
//                })
//            }

            start(InquiryHistoryFragment())
        }
    }

    private fun login(callback: SimpleCallBack? = null) {
        PishkhanCore.startPishkhanLogin(this) { success ->
            if (success) {
                callback?.invoke()
            } else {
                finish()
            }
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