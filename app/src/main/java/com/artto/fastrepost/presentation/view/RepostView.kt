package com.artto.fastrepost.presentation.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface RepostView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setImage(url: String)
}