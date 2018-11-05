package com.artto.fastrepost.di.application

import android.content.Context
import com.artto.fastrepost.ApplicationLoader
import com.artto.fastrepost.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: ApplicationLoader) {

    @Provides
    @ApplicationScope
    fun provideContext(): Context = application
}