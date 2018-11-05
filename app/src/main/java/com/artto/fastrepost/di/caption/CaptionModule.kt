package com.artto.fastrepost.di.caption

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.caption.CaptionPresenter
import dagger.Module
import dagger.Provides

@Module
class CaptionModule {

    @Provides
    @ViewScope
    fun providePresenter() = CaptionPresenter()

}