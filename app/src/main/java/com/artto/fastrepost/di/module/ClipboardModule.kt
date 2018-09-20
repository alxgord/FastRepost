package com.artto.fastrepost.di.module

import android.content.Context
import com.artto.fastrepost.di.scope.ApplicationScope
import com.artto.fastrepost.interact.ClipboardInteract
import dagger.Module
import dagger.Provides

@Module
class ClipboardModule {

    @Provides
    @ApplicationScope
    fun provideClipboardInteract(context: Context): ClipboardInteract = ClipboardInteract(context)
}