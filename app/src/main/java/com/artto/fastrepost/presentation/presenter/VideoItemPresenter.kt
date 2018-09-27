package com.artto.fastrepost.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.presentation.view.VideoItemView

@InjectViewState
class VideoItemPresenter : BaseMvpPresenter<VideoItemView>() {

    override fun onFirstViewAttach() {
        viewState.showVideo()
    }
}