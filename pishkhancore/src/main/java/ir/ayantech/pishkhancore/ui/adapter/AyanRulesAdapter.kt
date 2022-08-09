package ir.ayantech.pishkhancore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ir.ayantech.pishkhancore.databinding.RowRuleBinding
import ir.ayantech.pishkhancore.model.TermsAndCondition
import ir.ayantech.whygoogle.adapter.CommonAdapter
import ir.ayantech.whygoogle.adapter.CommonViewHolder

class AyanRulesAdapter(private val termsAndCondition: List<TermsAndCondition>) :
    CommonAdapter<TermsAndCondition, RowRuleBinding>(items = termsAndCondition) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RowRuleBinding
        get() = RowRuleBinding::inflate

    override fun onBindViewHolder(
        holder: CommonViewHolder<TermsAndCondition, RowRuleBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.mainView.rulesTitleTv.text = termsAndCondition[position].Key
        holder.mainView.rulesDescriptionTv.text = termsAndCondition[position].Value
    }
}