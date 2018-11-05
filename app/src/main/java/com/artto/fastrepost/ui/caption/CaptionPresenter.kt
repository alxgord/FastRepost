package com.artto.fastrepost.ui.caption

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.ui.base.BaseMvpPresenter

@InjectViewState
class CaptionPresenter : BaseMvpPresenter<CaptionView>() {

    fun onOkClicked() = viewState.dismissCaptionWindow()

    fun onBackPressed() = viewState.dismissCaptionWindow()

}