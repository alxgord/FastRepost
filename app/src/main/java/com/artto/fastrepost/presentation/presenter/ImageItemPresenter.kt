package com.artto.fastrepost.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.presentation.view.ImageItemView

@InjectViewState
class ImageItemPresenter : BaseMvpPresenter<ImageItemView>() {

    override fun onFirstViewAttach() = viewState.showImage()
}