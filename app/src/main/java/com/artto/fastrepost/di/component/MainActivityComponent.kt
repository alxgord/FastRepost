package com.artto.fastrepost.di.component

import com.artto.fastrepost.di.module.MainActivityModule
import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.presentation.presenter.MainPresenter
import dagger.Subcomponent

@Subcomponent( modules = [MainActivityModule::class] )
@ViewScope
interface MainActivityComponent {

    fun mainPresenter(): MainPresenter
}