package com.artto.fastrepost.ui.repost.image

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.ui.base.BaseMvpPresenter

@InjectViewState
class ImageItemPresenter : BaseMvpPresenter<ImageItemView>() {

    override fun onFirstViewAttach() = viewState.showImage()
}