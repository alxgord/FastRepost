package com.artto.fastrepost.di.main

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ViewScope
    fun providePresenter() = MainPresenter()
}