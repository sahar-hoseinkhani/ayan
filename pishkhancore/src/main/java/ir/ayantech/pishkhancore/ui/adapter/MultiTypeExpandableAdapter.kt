package ir.ayantech.pishkhancore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.databinding.RowAyanMultiTypeBinding
import ir.ayantech.whygoogle.adapter.CommonAdapter
import ir.ayantech.whygoogle.adapter.CommonViewHolder
import ir.ayantech.whygoogle.adapter.ExpandableItemAdapter
import ir.ayantech.whygoogle.adapter.OnItemClickListener

abstract class MultiTypeExpandableAdapter<P : MultiTypeItem<*>, RowLayout : ViewBinding>(
    items: List<P>,
    onItemClickListener: OnItemClickListener<Any>? = null
) :
    ExpandableItemAdapter<P, RowAyanMultiTypeBinding>(items, onItemClickListener) {

    abstract val visiblePartBindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RowLayout

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonViewHolder<P, RowAyanMultiTypeBinding> {
        return super.onCreateViewHolder(parent, viewType).also {
            it.mainView.visiblePartFl.addView(
                visiblePartBindingInflater.invoke(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).root
            )
        }
    }

    abstract fun getVisiblePartLayoutId(): Int

    override fun getExpandedLayoutId() = R.id.detailRcl

    override fun onBindViewHolder(holder: CommonViewHolder<P, RowAyanMultiTypeBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.mainView.detailRcl.layoutManager =
            LinearLayoutManager(holder.itemView.context, RecyclerView.VERTICAL, false)
        holder.mainView.detailRcl.isNestedScrollingEnabled = false
        holder.mainView.detailRcl.adapter = itemsToView[position].adapter
    }
}

open class MultiTypeItem<T>(val adapter: CommonAdapter<T, *>)

//class MultiTypeExpandableViewHolder<T, RowLayout : ViewBinding>(
//    viewBinding: _root_ide_package_.ir.ayantech.pishkhancore.databinding.RowAyanMultiTypeBinding,
//    insiderViewBinding: RowLayout,
//    onItemClickListener: OnItemClickListener<T>?
//) : CommonViewHolder<T, _root_ide_package_.ir.ayantech.pishkhancore.databinding.RowAyanMultiTypeBinding>(viewBinding, onItemClickListener)