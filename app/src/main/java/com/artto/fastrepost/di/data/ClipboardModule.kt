package com.artto.fastrepost.di.data

import android.content.Context
import com.artto.fastrepost.di.scope.ApplicationScope
import com.artto.fastrepost.data.clipboard.ClipboardRepository
import dagger.Module
import dagger.Provides

@Module
class ClipboardModule {

    @Provides
    @ApplicationScope
    fun provideClipboardInteract(context: Context): ClipboardRepository = ClipboardRepository(context)
}