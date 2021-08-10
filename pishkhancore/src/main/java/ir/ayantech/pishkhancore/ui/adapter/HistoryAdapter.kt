package ir.ayantech.pishkhancore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.databinding.RowAyanHistoryBinding
import ir.ayantech.pishkhancore.model.Transaction
import ir.ayantech.pishkhancore.model.getProductIcon
import ir.ayantech.whygoogle.adapter.CommonAdapter
import ir.ayantech.whygoogle.adapter.CommonViewHolder
import ir.ayantech.whygoogle.adapter.OnItemClickListener
import ir.ayantech.whygoogle.helper.formatAmount

class HistoryAdapter(
    items: List<Transaction>,
    onItemClickListener: OnItemClickListener<Transaction>? = null
) : CommonAdapter<Transaction, RowAyanHistoryBinding>(items, onItemClickListener) {

    override fun onBindViewHolder(
        holder: CommonViewHolder<Transaction, RowAyanHistoryBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        itemsToView[position].let {
            holder.mainView.inquiryDescriptionTv.text = it.Title
            holder.mainView.billAmountTv.text = it.Amount.formatAmount()
            holder.mainView.dateTv.text =
                it.DateTime.Persian.DateFormatted + " | " + it.DateTime.Time

            it.Icon?.Name?.getProductIcon()?.let { it1 ->
                holder.mainView.inquiryTypeIcon.setImageResource(
                    it1
                )
            }
            holder.itemView.setBackgroundResource(if ((position) % 2 == 0) R.color.ayanColorLightGray else R.color.white)
        }
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.drop_down
            )
        )
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RowAyanHistoryBinding
        get() = RowAyanHistoryBinding::inflate
}