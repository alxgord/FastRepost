package com.artto.fastrepost.di.history

import com.artto.fastrepost.data.database.history.HistoryRepository
import com.artto.fastrepost.data.instagram.InstagramRepository
import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.history.HistoryInteract
import com.artto.fastrepost.ui.history.HistoryPresenter
import dagger.Module
import dagger.Provides

@Module
class HistoryModule {

    @Provides
    @ViewScope
    fun provideInteract(historyRepository: HistoryRepository, instagramRepository: InstagramRepository) =
            HistoryInteract(historyRepository, instagramRepository)

    @Provides
    @ViewScope
    fun providePresenter(interact: HistoryInteract) = HistoryPresenter(interact)

}