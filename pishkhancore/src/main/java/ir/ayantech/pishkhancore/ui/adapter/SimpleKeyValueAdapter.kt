package ir.ayantech.pishkhancore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ir.ayantech.pishkhancore.databinding.RowBillDetailBinding
import ir.ayantech.pishkhancore.model.KeyValue
import ir.ayantech.whygoogle.adapter.CommonAdapter
import ir.ayantech.whygoogle.adapter.CommonViewHolder
import ir.ayantech.whygoogle.adapter.OnItemClickListener
import ir.ayantech.whygoogle.helper.changeVisibility

class SimpleKeyValueAdapter(
    items: List<KeyValue>,
    onItemClickListener: OnItemClickListener<KeyValue>? = null
) : CommonAdapter<KeyValue, RowBillDetailBinding>(items, onItemClickListener) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RowBillDetailBinding
        get() = RowBillDetailBinding::inflate

    override fun onBindViewHolder(
        holder: CommonViewHolder<KeyValue, RowBillDetailBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.mainView.keyTv.text = itemsToView[position].Key
        holder.mainView.valueTv.text = itemsToView[position].Value
        holder.mainView.valueTv.changeVisibility(itemsToView[position].Value?.isNotEmpty() == true)
        if (itemsToView[position].textColor != 0) {
            ContextCompat.getColor(holder.itemView.context, itemsToView[position].textColor).let {
                holder.mainView.keyTv.setTextColor(it)
                holder.mainView.valueTv.setTextColor(it)
            }
        }
    }
}