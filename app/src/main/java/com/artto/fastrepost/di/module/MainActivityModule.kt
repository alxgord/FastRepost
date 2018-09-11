package com.artto.fastrepost.di.module

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.presentation.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ViewScope
    fun providePresenter() = MainPresenter()
}