package com.artto.fastrepost.di.module

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.presentation.presenter.VideoItemPresenter
import dagger.Module
import dagger.Provides

@Module
class VideoItemModule {

    @Provides
    @ViewScope
    fun providePresenter() = VideoItemPresenter()
}