package com.artto.fastrepost.di.module

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.interact.ClipboardInteract
import com.artto.fastrepost.interact.InstagramInteract
import com.artto.fastrepost.interact.RepostInteract
import com.artto.fastrepost.presentation.presenter.RepostPresenter
import dagger.Module
import dagger.Provides

@Module
class RepostModule {

    @Provides
    @ViewScope
    fun providePresenter(interact: RepostInteract) = RepostPresenter(interact)

    @Provides
    @ViewScope
    fun provideInteract(clipboardInteract: ClipboardInteract, instagramInteract: InstagramInteract) =
            RepostInteract(clipboardInteract, instagramInteract)
}