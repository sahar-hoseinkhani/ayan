package ir.ayantech.pishkhancore.ui.adapter

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatButton
import ir.ayantech.advertisement.core.AdvertisementCore
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.databinding.RowAyanHistoryBinding
import ir.ayantech.pishkhancore.databinding.RowAyanHistoryNativeAdBinding
import ir.ayantech.pishkhancore.model.Transaction
import ir.ayantech.pishkhancore.model.getProductIcon
import ir.ayantech.whygoogle.adapter.MultiViewTypeAdapter
import ir.ayantech.whygoogle.adapter.MultiViewTypeViewHolder
import ir.ayantech.whygoogle.adapter.OnItemClickListener
import ir.ayantech.whygoogle.fragment.ViewBindingInflater
import ir.ayantech.whygoogle.helper.formatAmount
import ir.ayantech.whygoogle.helper.makeVisible

class AyanHistoryAdapter(
    items: List<Any>,
    val showAds: Boolean,
    onItemClickListener: OnItemClickListener<Any>? = null
) : MultiViewTypeAdapter<Any>(items, onItemClickListener) {

    private val CONTENT = 0
    private val AD = 1

    override fun getItemViewType(position: Int): Int {
        return if (showAds && position == 1) {
            AD
        } else {
            CONTENT
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MultiViewTypeViewHolder<Any> {
        return super.onCreateViewHolder(parent, viewType).also {
            (it.viewBinding as? RowAyanHistoryNativeAdBinding)?.let { rowMainNativeAd ->
                it.registerClickListener(rowMainNativeAd.nativeAdLl) { nativeAdLl ->
                    nativeAdLl.findViewById<AppCompatButton>(R.id.adivery_call_to_action)
                        .performClick()
                }
                rowMainNativeAd.nativeAdLl.addView(
                    AdvertisementCore.requestNativeAds(
                        parent.context,
                        R.layout.ayan_native_ad,
                    ) {
                        rowMainNativeAd.nativeAdLl.makeVisible()
                    }
                )
            }
        }
    }

    override fun onBindViewHolder(
        holder: MultiViewTypeViewHolder<Any>, position: Int
    ) {
        super.onBindViewHolder(holder, position)
        when (getItemViewType(position)) {
            CONTENT -> {
                (holder.viewBinding as? RowAyanHistoryBinding)?.let {
                    (itemsToView[position] as Transaction).let { data ->
                        it.inquiryDescriptionTv.text = data.Title
                        it.billAmountTv.text = data.Amount.formatAmount()
                        it.dateTv.text =
                            data.DateTime.Persian.DateFormatted + " | " + data.DateTime.Time

                        data.Icon?.Name?.getProductIcon()?.let { it1 ->
                            it.inquiryTypeIcon.setImageResource(
                                it1
                            )
                        }
                    }
                }
            }
        }

        holder.itemView.setBackgroundResource(if ((position) % 2 == 0) R.color.ayanColorLightGray else R.color.white)

        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.drop_down
            )
        )
    }

    override fun getViewInflaterForViewType(viewType: Int): ViewBindingInflater {
        return if (viewType == AD) RowAyanHistoryNativeAdBinding::inflate
        else RowAyanHistoryBinding::inflate
    }
}