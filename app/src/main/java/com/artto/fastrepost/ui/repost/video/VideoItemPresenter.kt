package com.artto.fastrepost.ui.repost.video

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.ui.base.BaseMvpPresenter

@InjectViewState
class VideoItemPresenter : BaseMvpPresenter<VideoItemView>() {

    override fun onFirstViewAttach() {
        viewState.showVideo()
    }

    fun onVideoPlayerClicked(isPlaying: Boolean) {
        if (isPlaying) viewState.pauseVideo()
        else viewState.playVideo()
    }
}