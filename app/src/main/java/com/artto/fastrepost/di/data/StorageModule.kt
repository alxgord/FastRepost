package com.artto.fastrepost.di.data

import android.content.Context
import com.artto.fastrepost.data.storage.StorageManager
import com.artto.fastrepost.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class StorageModule {

    @Provides
    @ApplicationScope
    fun provideStorageManager(context: Context, client: OkHttpClient) = StorageManager(context, client)
}