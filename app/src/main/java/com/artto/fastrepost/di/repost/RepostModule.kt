package com.artto.fastrepost.di.repost

import com.artto.fastrepost.data.storage.StorageManager
import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.data.clipboard.ClipboardRepository
import com.artto.fastrepost.data.database.history.HistoryRepository
import com.artto.fastrepost.data.instagram.InstagramRepository
import com.artto.fastrepost.data.properties.PropertiesManager
import com.artto.fastrepost.ui.repost.RepostInteract
import com.artto.fastrepost.ui.repost.RepostPresenter
import dagger.Module
import dagger.Provides

@Module
class RepostModule {

    @Provides
    @ViewScope
    fun providePresenter(interact: RepostInteract) = RepostPresenter(interact)

    @Provides
    @ViewScope
    fun provideInteract(
            clipboardRepository: ClipboardRepository,
            storageManager: StorageManager,
            historyRepository: HistoryRepository,
            instagramRepository: InstagramRepository,
            propertiesManager: PropertiesManager) =
            RepostInteract(
                    clipboardRepository,
                    storageManager,
                    historyRepository,
                    instagramRepository,
                    propertiesManager
            )
}