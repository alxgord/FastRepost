package com.artto.fastrepost.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ImageItemView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showImage()
}