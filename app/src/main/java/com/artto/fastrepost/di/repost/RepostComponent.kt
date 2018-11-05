package com.artto.fastrepost.di.repost

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.repost.RepostPresenter
import dagger.Subcomponent

@Subcomponent(modules = [RepostModule::class])
@ViewScope
interface RepostComponent {

    fun repostPresenter(): RepostPresenter
}