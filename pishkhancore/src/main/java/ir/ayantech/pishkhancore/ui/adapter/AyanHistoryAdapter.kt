package ir.ayantech.pishkhancore.ui.adapter

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatButton
import com.adivery.sdk.AdiveryNativeAdView
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

class AyanHistoryAdapter(
    items: List<Any>,
    onItemClickListener: OnItemClickListener<Any>? = null
) : MultiViewTypeAdapter<Any>(items, onItemClickListener) {

    private val CONTENT = 0
    private val AD = 1

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is Transaction)
            CONTENT
        else AD
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
            }
        }
    }

    override fun onBindViewHolder(
        holder: MultiViewTypeViewHolder<Any>, position: Int
    ) {
        super.onBindViewHolder(holder, position)
        when (getItemViewType(position)) {
            AD -> {
                (holder.viewBinding as? RowAyanHistoryNativeAdBinding)?.let {
                    (itemsToView[position] as ViewGroup).let { adView ->
                        //The specified child already has a parent. You must call removeView() on the child's parent first
                        it.nativeAdLl.removeAllViews()
                        it.nativeAdLl.addView(adView)
                    }
                }
            }
            CONTENT -> {
                (holder.viewBinding as? RowAyanHistoryBinding)?.let {
                    (itemsToView[position] as Transaction).let { data ->
                        it.inquiryDescriptionTv.text = data.Title
                        it.billAmountTv.text = attachedContext.resources.getString(R.string.custom_unit, data.Amount)
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

    fun updateItems(items: List<Any>) {
        this.itemsToView = items
        this.items = items
        notifyDataSetChanged()
    }
}