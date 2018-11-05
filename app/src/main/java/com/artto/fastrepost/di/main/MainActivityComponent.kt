package com.artto.fastrepost.di.main

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.main.MainPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class])
@ViewScope
interface MainActivityComponent {

    fun mainPresenter(): MainPresenter
}