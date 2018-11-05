package com.artto.fastrepost.ui.main

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.artto.fastrepost.ui.base.BaseMvpView

interface MainView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setViewPagerPosition(position: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setBottomNavigationItem(itemId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun requestPermission()
}