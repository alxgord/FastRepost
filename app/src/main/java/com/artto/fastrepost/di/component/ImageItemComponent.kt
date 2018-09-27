package com.artto.fastrepost.di.component

import com.artto.fastrepost.di.module.ImageItemModule
import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.presentation.presenter.ImageItemPresenter
import dagger.Subcomponent

@Subcomponent(modules = [ImageItemModule::class])
@ViewScope
interface ImageItemComponent {

    fun imageItemPresenter(): ImageItemPresenter
}