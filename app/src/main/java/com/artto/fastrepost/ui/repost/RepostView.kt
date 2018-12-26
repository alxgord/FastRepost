package com.artto.fastrepost.ui.repost

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.artto.fastrepost.ui.base.BaseMvpView

interface RepostView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(textResId: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgressBar(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showIndicator(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyDataSetChanged()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCaption(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun sendIntentToInstagram(filePath: String, mediaType: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showHint(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showCaptionWindow(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showRateDialog()

}