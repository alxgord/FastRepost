package com.artto.fastrepost.presentation.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView : BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun navigateToRepost()
}