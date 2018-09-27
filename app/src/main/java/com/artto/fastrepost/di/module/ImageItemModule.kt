package com.artto.fastrepost.di.module

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.presentation.presenter.ImageItemPresenter
import dagger.Module
import dagger.Provides

@Module
class ImageItemModule {

    @Provides
    @ViewScope
    fun providePresenter() = ImageItemPresenter()
}