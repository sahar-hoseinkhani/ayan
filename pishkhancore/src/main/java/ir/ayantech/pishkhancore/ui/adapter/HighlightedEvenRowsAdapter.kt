package ir.ayantech.pishkhancore.ui.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.databinding.RowAyanBillDetailBinding
import ir.ayantech.pishkhancore.databinding.RowAyanRecyclerViewBinding
import ir.ayantech.pishkhancore.model.KeyValue
import ir.ayantech.pushsdk.helper.ShareHelper
import ir.ayantech.whygoogle.adapter.CommonAdapter
import ir.ayantech.whygoogle.adapter.CommonViewHolder
import ir.ayantech.whygoogle.adapter.OnItemClickListener
import ir.ayantech.whygoogle.helper.changeVisibility
import ir.ayantech.whygoogle.helper.verticalSetup

open class HighlightedEvenRowsAdapter(
    items: List<KeyValue>,
    private val startHighlightFrom: Int = 0,
    private val boldText: List<Int> = listOf(),
    private val noBold: Boolean = false,
    onItemClickListener: OnItemClickListener<KeyValue>? = null
) : CommonAdapter<KeyValue, RowAyanBillDetailBinding>(items, onItemClickListener) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonViewHolder<KeyValue, RowAyanBillDetailBinding> {
        return super.onCreateViewHolder(parent, viewType).also {
            it.registerClickListener(it.mainView.rowCopyIv) { _ ->
                ShareHelper.copyToClipBoard(parent.context, it.item?.Value)
                Toast.makeText(
                    parent.context,
                    "${it.item?.Key} در حافظه کپی شد.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onBindViewHolder(
        holder: CommonViewHolder<KeyValue, RowAyanBillDetailBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.mainView.rowCopyIv.changeVisibility(canUserCopyTheValue(itemsToView[position]))
        if (!noBold)
            holder.mainView.keyTv.setTypeface(holder.mainView.keyTv.typeface, Typeface.BOLD)
        if (boldText.contains(position)) {
            holder.mainView.valueTv.setTypeface(holder.mainView.valueTv.typeface, Typeface.BOLD)
        } else {
            holder.mainView.valueTv.setTypeface(holder.mainView.valueTv.typeface, Typeface.NORMAL)
        }
        holder.mainView.keyTv.text = itemsToView[position].Key
        holder.mainView.valueTv.text = itemsToView[position].Value
        holder.mainView.valueTv.changeVisibility(!itemsToView[position].Value.isNullOrEmpty())
        if (startHighlightFrom < 0) return
        holder.itemView.setBackgroundResource(
            if ((position + startHighlightFrom) % 2 == 0) R.color.ayanColorHighlight
            else R.color.white
        )
    }

    open fun canUserCopyTheValue(keyValue: KeyValue): Boolean {
        return when (keyValue.Key) {
            "شناسه قبض" -> true
            "شناسه پرداخت" -> true
            else -> keyValue.canCopy
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RowAyanBillDetailBinding
        get() = RowAyanBillDetailBinding::inflate
}

class HighlightedCascadeKeyValueAdapter(
    items: List<List<KeyValue>>,
    onItemClickListener: OnItemClickListener<List<KeyValue>>? = null
) : CommonAdapter<List<KeyValue>, RowAyanRecyclerViewBinding>(items, onItemClickListener) {

    override fun onBindViewHolder(
        holder: CommonViewHolder<List<KeyValue>, RowAyanRecyclerViewBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.mainView.rootRv.verticalSetup()
        holder.mainView.rootRv.adapter =
            HighlightedEvenRowsAdapter(itemsToView[position], -1)
        holder.itemView.setBackgroundResource(
            if ((position) % 2 == 0) R.color.ayanColorHighlight
            else R.color.white
        )
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RowAyanRecyclerViewBinding
        get() = RowAyanRecyclerViewBinding::inflate
}