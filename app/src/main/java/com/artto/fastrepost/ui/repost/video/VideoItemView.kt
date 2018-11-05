package com.artto.fastrepost.ui.repost.video

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.artto.fastrepost.ui.base.BaseMvpView

interface VideoItemView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showVideo()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun playVideo()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun pauseVideo()
}