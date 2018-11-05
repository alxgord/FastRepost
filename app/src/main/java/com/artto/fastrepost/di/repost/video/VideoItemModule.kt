package com.artto.fastrepost.di.repost.video

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.repost.video.VideoItemPresenter
import dagger.Module
import dagger.Provides

@Module
class VideoItemModule {

    @Provides
    @ViewScope
    fun providePresenter() = VideoItemPresenter()
}