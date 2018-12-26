package com.artto.fastrepost.ui.dialog.rate

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.artto.fastrepost.ui.base.BaseMvpView

interface RateView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun dismissDialog()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToPlayMarket()

}