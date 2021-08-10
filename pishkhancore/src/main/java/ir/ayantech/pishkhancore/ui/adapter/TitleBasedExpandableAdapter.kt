package ir.ayantech.pishkhancore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.databinding.RowAyanJusticeSharesBinding
import ir.ayantech.pishkhancore.databinding.RowAyanMultiTypeBinding
import ir.ayantech.whygoogle.adapter.CommonAdapter
import ir.ayantech.whygoogle.adapter.CommonViewHolder
import ir.ayantech.whygoogle.adapter.OnItemClickListener
import ir.ayantech.whygoogle.helper.changeVisibility


class TitleBasedExpandableAdapter(
    items: List<TitleBasedMultiTypeItem<*>>,
    onItemClickListener: OnItemClickListener<Any>? = null
) : MultiTypeExpandableAdapter<TitleBasedMultiTypeItem<*>, RowAyanJusticeSharesBinding>(
    items,
    onItemClickListener
) {

    override fun getVisiblePartLayoutId() = R.layout.row_ayan_justice_shares

    override fun onBindViewHolder(
        holder: CommonViewHolder<TitleBasedMultiTypeItem<*>, RowAyanMultiTypeBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.itemView.findViewById<TextView>(R.id.itemTitleTv).text = itemsToView[position].title
        holder.itemView.findViewById<TextView>(R.id.itemDescriptionTv)
            .changeVisibility(itemsToView[position].description != null)
        holder.itemView.findViewById<TextView>(R.id.itemDescriptionTv).text =
            itemsToView[position].description
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RowAyanMultiTypeBinding
        get() = RowAyanMultiTypeBinding::inflate

    override val visiblePartBindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RowAyanJusticeSharesBinding
        get() = RowAyanJusticeSharesBinding::inflate
}

class TitleBasedMultiTypeItem<T>(
    val title: String,
    adapter: CommonAdapter<T, *>,
    val description: String? = null
) :
    MultiTypeItem<T>(adapter)