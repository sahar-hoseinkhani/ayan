package ir.ayantech.pishkhansample.ui.activity

import ir.ayantech.pishkhancore.model.PaymentHistoryGetTransactionInfoOutput
import ir.ayantech.pishkhancore.ui.fragment.AyanHistoryDetailFragment

class DetailFragment : AyanHistoryDetailFragment() {

    override var transaction: PaymentHistoryGetTransactionInfoOutput? = null

    override fun onCreate() {
        super.onCreate()

    }
}