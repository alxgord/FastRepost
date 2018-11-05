package com.artto.fastrepost.ui.history

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.artto.fastrepost.ui.base.BaseMvpView

interface HistoryView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyDataSetChanged()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyItemRemoved(position: Int)
}