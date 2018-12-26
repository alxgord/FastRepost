package com.artto.fastrepost.ui.dialog.rate

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.ui.base.BaseMvpPresenter

@InjectViewState
class RatePresenter(private val interact: RateInteract) : BaseMvpPresenter<RateView>() {

    fun onRateClicked() {
        interact.setRatedApp(true)
        viewState.navigateToPlayMarket()
    }

    fun onRemindClicked() = viewState.dismissDialog()

}