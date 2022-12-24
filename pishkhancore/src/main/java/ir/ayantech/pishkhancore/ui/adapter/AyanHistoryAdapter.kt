package ir.ayantech.pishkhancore.ui.adapter

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.databinding.RowAyanHistoryBinding
import ir.ayantech.pishkhancore.databinding.RowAyanHistoryNativeAdBinding
import ir.ayantech.pishkhancore.model.Transaction
import ir.ayantech.pishkhancore.model.getProductIcon
import ir.ayantech.whygoogle.adapter.MultiViewTypeAdapter
import ir.ayantech.whygoogle.adapter.MultiViewTypeViewHolder
import ir.ayantech.whygoogle.adapter.OnItemClickListener
import ir.ayantech.whygoogle.fragment.ViewBindingInflater
import ir.tafreshiali.whyoogle_ads.databinding.RowNativeAdInListPlaceHolderBinding
import ir.tafreshiali.whyoogle_ads.extension.registerClickForNativeAdvertisement

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
        return super.onCreateViewHolder(parent, viewType).also { holder ->
            when (holder.viewBinding) {
                is RowNativeAdInListPlaceHolderBinding -> {
                    holder.registerClickForNativeAdvertisement {
                        //TODO MAYBE WE WANT TO SEND ANALYTICS EVENT LATER ON
                    }
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
                (holder.viewBinding as? RowNativeAdInListPlaceHolderBinding)?.let {
                    (itemsToView[position] as ViewGroup).let { adView ->
                        //The specified child already has a parent. You must call removeView() on the child's parent first
                        it.nativeAdView.removeAllViews()
                        it.nativeAdView.addView(adView)
                    }
                }
            }
            CONTENT -> {
                (holder.viewBinding as? RowAyanHistoryBinding)?.let {
                    (itemsToView[position] as Transaction).let { data ->
                        it.inquiryDescriptionTv.text = data.Title
                        it.billAmountTv.text =
                            attachedContext.resources.getString(R.string.custom_unit, data.Amount)
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
        return if (viewType == AD) RowNativeAdInListPlaceHolderBinding::inflate
        else RowAyanHistoryBinding::inflate
    }

    fun updateItems(items: List<Any>) {
        this.itemsToView = items
        this.items = items
        notifyDataSetChanged()
    }
}