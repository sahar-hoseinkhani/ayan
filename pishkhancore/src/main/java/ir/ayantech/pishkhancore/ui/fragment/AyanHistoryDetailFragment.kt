package ir.ayantech.pishkhancore.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ir.ayantech.advertisement.core.AdvertisementCore
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.databinding.FragmentAyanHistoryDetailBinding
import ir.ayantech.pishkhancore.helper.*
import ir.ayantech.pishkhancore.helper.safeGet
import ir.ayantech.pishkhancore.helper.setHtmlText
import ir.ayantech.pishkhancore.model.PaymentHistoryGetTransactionInfoOutput
import ir.ayantech.pishkhancore.model.getProductIcon
import ir.ayantech.pishkhancore.ui.adapter.HighlightedEvenRowsAdapter
import ir.ayantech.pishkhancore.ui.adapter.TitleBasedExpandableAdapter
import ir.ayantech.pishkhancore.ui.adapter.TitleBasedMultiTypeItem
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import ir.ayantech.whygoogle.fragment.WhyGoogleFragmentTransactionAnimation
import ir.ayantech.whygoogle.helper.*

abstract class AyanHistoryDetailFragment : WhyGoogleFragment<FragmentAyanHistoryDetailBinding>() {

    abstract var transaction: PaymentHistoryGetTransactionInfoOutput?
    var onGetHistoryDetails: ((ViewGroup)-> Unit)? = null

    override fun onCreate() {
        super.onCreate()

        onGetHistoryDetails?.invoke(binding.bannerRl)

//        PishkhanCore.getAppConfigAdvertisement(requireContext()) {
//            binding.bannerRl.changeVisibility(it.Active)
//            if (it.Active) {
//                AdvertisementCore.requestBannerAds(requireContext(), binding.bannerRl)
//            }
//        }

        accessViews {
            transaction?.let { transaction ->
                amountTv.text = transaction.Amount.formatAmount("")
                titleTv.text = transaction.Title
                transaction.Icon?.Name?.getProductIcon()?.let { productIv.setImageResource(it) }
                dateTimeTv.text =
                    (transaction.DateTime.Persian.DateFormatted + " | " + transaction.DateTime.Time).trim()
                detailsRv.verticalSetup()
                if (transaction.Details != null) {
                    detailsRv.addDivider()
                    detailsRv.adapter = TitleBasedExpandableAdapter(
                        transaction.Details.map {
                            TitleBasedMultiTypeItem(
                                it.first().Key,
                                HighlightedEvenRowsAdapter(it.subList(1, it.size - 1)),
                                it.first().Value
                            )
                        }
                    )
                } else {
                    detailsRv.adapter = transaction.ExtraInfo?.let {
                        HighlightedEvenRowsAdapter(it, 1)
                    }
                }
                descriptionTv.setHtmlText(transaction.Description)
                printLl.setOnClickListener {
                    transaction.ReceiptUrl?.openUrl(requireContext())
                }
                if (transaction.TraceNumberReviewLink != null) {
                    (binding.root as ViewGroup).delayedTransition()
                    followUpLl.makeVisible()
                    followUpLl.setOnClickListener {
                        transaction.TraceNumberReviewLink.openUrl(requireContext())
                    }
                }
                transaction.Buttons?.let { buttons ->
                    if (buttons.isEmpty()) return@let
                    followUpLl.makeGone()
                    buttons.first().let {
                        iv1.loadFromString(it.Icon)
                        tv1.text = it.Title
                        printLl.setOnClickListener { _ ->
                            it.Link.handlePishkhanLink(requireActivity())
                        }
                    }
                    buttons.safeGet(1)?.let {
                        followUpLl.makeVisible()
                        iv2.loadFromString(it.Icon)
                        tv2.text = it.Title
                        followUpLl.setOnClickListener { _ ->
                            it.Link.handlePishkhanLink(requireActivity())
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed(): Boolean {
        pop()
        return true
    }

    override fun getFragmentTransactionAnimation(): WhyGoogleFragmentTransactionAnimation? {
        return WhyGoogleFragmentTransactionAnimation(
            R.anim.h_fragment_enter,
            R.anim.h_fragment_exit,
            R.anim.h_fragment_pop_enter,
            R.anim.h_fragment_pop_exit
        )
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAyanHistoryDetailBinding
        get() = FragmentAyanHistoryDetailBinding::inflate
}