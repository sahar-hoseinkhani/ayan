package ir.ayantech.pishkhancore.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ir.ayantech.pishkhancore.R
import ir.ayantech.pishkhancore.databinding.FragmentPostTrackingResultBinding
import ir.ayantech.pishkhancore.helper.getKeyValueExtraInfo
import ir.ayantech.pishkhancore.model.KeyValue
import ir.ayantech.pishkhancore.model.post_package.InquiryPostPackageTrackingOutput
import ir.ayantech.pishkhancore.model.post_package.PostResult
import ir.ayantech.pishkhancore.model.post_package.getEventShowKeyValue
import ir.ayantech.pishkhancore.ui.adapter.*
import ir.ayantech.whygoogle.fragment.WhyGoogleFragment
import ir.ayantech.whygoogle.helper.addDivider
import ir.ayantech.whygoogle.helper.verticalSetup

open class PostTrackingResultFragment : WhyGoogleFragment<FragmentPostTrackingResultBinding>() {

    var output: InquiryPostPackageTrackingOutput? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostTrackingResultBinding
        get() = FragmentPostTrackingResultBinding::inflate

    override fun onCreate() {
        super.onCreate()
        output?.Result?.let { postResult ->
            initHeaderRecyclerView(postResult = postResult)
            initMainRecyclerView(postResult = postResult)
        }
    }


    private fun initHeaderRecyclerView(postResult: PostResult) {
        accessViews {
            headerRv.apply {
                verticalSetup()
                adapter = SimpleKeyValueAdapter(listOf(
                    KeyValue(
                        context.getString(R.string.tv_post_package_number),
                        postResult.PackageNumber
                    ),
                    KeyValue(
                        context.getString(R.string.tv_service_type),
                        postResult.ServiceType
                    ),
                    KeyValue(context.getString(R.string.tv_weight), postResult.Weight),
                    KeyValue(
                        context.getString(R.string.tv_insurance_amount),
                        postResult.InsuranceCost
                    ),
                    KeyValue(
                        context.getString(R.string.tv_post_price),
                        postResult.PostCost
                    ),
                    KeyValue(
                        context.getString(R.string.tv_acceptance_date_time),
                        postResult.AcceptanceDateTime?.Persian?.DateFormatted
                    )
                ).filter { !it.Value.isNullOrEmpty() }.onEach { it.textColor = R.color.PostTrackingResultFragmentHeaderRvItemTextColor })

            }
        }
    }

    private fun initMainRecyclerView(postResult: PostResult) {
        accessViews {
            recyclerView.apply {
                verticalSetup()
                addDivider()

                adapter = TitleBasedExpandableAdapter(
                    arrayListOf<TitleBasedMultiTypeItem<*>>(
                        TitleBasedMultiTypeItem(
                            context.getString(R.string.tv_post_tracking_status),
                            HighlightedEvenRowsAdapter(
                                postResult.Events?.last()?.ExtraInfo?.getKeyValueExtraInfo()
                                    ?: listOf()
                            )
                        ),
                        TitleBasedMultiTypeItem(
                            context.getString(R.string.tv_post_package_tracking_detail),
                            HighlightedCascadeKeyValueAdapter(
                                postResult.Events?.map {
                                    it.getEventShowKeyValue(
                                        dateTime = context.getString(R.string.tv_date_time),
                                        place = context.getString(R.string.tv_place)
                                    )
                                } ?: listOf()
                            )
                        )
                    )
                )
            }
        }
    }
}