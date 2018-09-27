package com.artto.fastrepost.di.component

import com.artto.fastrepost.di.module.VideoItemModule
import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.presentation.presenter.VideoItemPresenter
import dagger.Subcomponent

@Subcomponent(modules = [VideoItemModule::class])
@ViewScope
interface VideoItemComponent {

    fun videoItemPresenter(): VideoItemPresenter
}