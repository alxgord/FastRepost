package com.artto.fastrepost.ui.caption

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.artto.fastrepost.ui.base.BaseMvpView

interface CaptionView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun dismissCaptionWindow()

}