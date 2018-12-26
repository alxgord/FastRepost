package com.artto.fastrepost.di.data

import android.content.Context
import android.content.SharedPreferences
import com.artto.fastrepost.data.properties.PropertiesDataStore
import com.artto.fastrepost.data.properties.PropertiesManager
import com.artto.fastrepost.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class PropertiesModule {

    @Provides
    @ApplicationScope
    fun provideSharedPreference(context: Context) =
            context.getSharedPreferences("properties", Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun providePropertiesDataStore(sharedPreferences: SharedPreferences) =
            PropertiesDataStore(sharedPreferences)

    @Provides
    @ApplicationScope
    fun providePropertiesManager(propertiesDataStore: PropertiesDataStore) =
            PropertiesManager(propertiesDataStore)

}