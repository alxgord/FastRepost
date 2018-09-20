package com.artto.fastrepost.di.component

import com.artto.fastrepost.di.module.RepostModule
import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.presentation.presenter.RepostPresenter
import dagger.Subcomponent

@Subcomponent(modules = [RepostModule::class])
@ViewScope
interface RepostComponent {

    fun repostPresenter(): RepostPresenter
}