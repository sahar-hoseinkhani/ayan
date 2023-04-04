package ir.ayantech.pishkhancore.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.core.PishkhanCore
import ir.ayantech.pishkhancore.databinding.RowAyanInquiryHistoryBinding
import ir.ayantech.pishkhancore.helper.InquiryHistoryCallBack
import ir.ayantech.pishkhancore.model.EndPoint
import ir.ayantech.pishkhancore.model.InquiryBookmarkItemInput
import ir.ayantech.pishkhancore.model.InquiryHistory
import ir.ayantech.pishkhancore.ui.dialog.AyanSaveInquiryDialog
import ir.ayantech.whygoogle.adapter.MultiViewTypeAdapter
import ir.ayantech.whygoogle.adapter.MultiViewTypeViewHolder
import ir.ayantech.whygoogle.adapter.OnItemClickListener
import ir.ayantech.whygoogle.fragment.ViewBindingInflater
import ir.tafreshiali.whyoogle_ads.databinding.RowNativeAdInListPlaceHolderBinding
import ir.tafreshiali.whyoogle_ads.extension.loadAdViewInAdapter
import ir.tafreshiali.whyoogle_ads.extension.registerClickForNativeAdvertisement

class AyanInquiryHistoryAdapter(
    private val mcontext: Context,
    items: List<Any>,
    private val onChange: InquiryHistoryCallBack,
    onItemClickListener: OnItemClickListener<Any>?
) : MultiViewTypeAdapter<Any>(items, onItemClickListener) {

    private val CONTENT = 0
    private val AD = 1

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is InquiryHistory)
            CONTENT
        else AD
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MultiViewTypeViewHolder<Any> {
        return super.onCreateViewHolder(parent, viewType).also {

            it.registerClickForNativeAdvertisement()


            (it.viewBinding as? RowAyanInquiryHistoryBinding)?.let { rowInquiryHistory ->
                it.registerClickListener(rowInquiryHistory.moreIv) { _ ->
                    (it.item as? InquiryHistory)?.let {
                        AyanSaveInquiryDialog(
                            mcontext,
                            it,
                            onChange
                        ).show()
                    }
                }

                it.registerClickListener(rowInquiryHistory.pinIv) { _ ->
                    (it.item as? InquiryHistory)?.let { inquiryHistory ->
                        PishkhanCore.ayanApi?.simpleCall<InquiryHistory>(
                            EndPoint.InquiryBookmarkItem,
                            InquiryBookmarkItemInput(
                                !inquiryHistory.Favorite,
                                inquiryHistory.NotificationPermission,
                                inquiryHistory.Type.Name,
                                inquiryHistory.Note,
                                inquiryHistory.UniqueID
                            )
                        ) {
                            onChange(inquiryHistory)
                        }
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
                holder.loadAdViewInAdapter(advertisementItem = itemsToView[position])
            }

            CONTENT -> {
                (holder.viewBinding as? RowAyanInquiryHistoryBinding)?.let {
                    (itemsToView[position] as InquiryHistory).let { data ->
                        it.inquiryHistoryNameTv.text = data.Note
                        it.inquiryHistoryValueTv.text = data.QueryValue
                        it.pinIv.setColorFilter(
                            if (data.Favorite)
                                ContextCompat.getColor(
                                    mcontext,
                                    R.color.AyanInquiryHistoryAdapterPinIvIsFavorite
                                )
                            else
                                ContextCompat.getColor(
                                    mcontext,
                                    R.color.AyanInquiryHistoryAdapterPinIvIsNotFavorite
                                )
                        )
                    }
                }
            }
        }
    }


    override fun getViewInflaterForViewType(viewType: Int): ViewBindingInflater {
        return if (viewType == AD) RowNativeAdInListPlaceHolderBinding::inflate
        else RowAyanInquiryHistoryBinding::inflate
    }

    fun updateItems(items: List<Any>) {
        this.itemsToView = items
        this.items = items
        notifyItemInserted(1)
    }
}