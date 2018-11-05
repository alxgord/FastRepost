package com.artto.fastrepost.ui.repost.image

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.artto.fastrepost.ui.base.BaseMvpView

interface ImageItemView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showImage()
}