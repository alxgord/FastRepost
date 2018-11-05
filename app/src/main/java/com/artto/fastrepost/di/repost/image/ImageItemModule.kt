package com.artto.fastrepost.di.repost.image

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.repost.image.ImageItemPresenter
import dagger.Module
import dagger.Provides

@Module
class ImageItemModule {

    @Provides
    @ViewScope
    fun providePresenter() = ImageItemPresenter()
}