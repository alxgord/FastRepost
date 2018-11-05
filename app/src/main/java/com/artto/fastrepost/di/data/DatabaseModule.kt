package com.artto.fastrepost.di.data

import android.arch.persistence.room.Room
import android.content.Context
import com.artto.fastrepost.data.database.Database
import com.artto.fastrepost.data.database.DatabaseConstants
import com.artto.fastrepost.data.database.history.HistoryDao
import com.artto.fastrepost.data.database.history.HistoryRepository
import com.artto.fastrepost.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(context: Context) =
            Room.databaseBuilder(context, Database::class.java, DatabaseConstants.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

    @Provides
    @ApplicationScope
    fun provideHistoryDao(database: Database) = database.historyDao()

    @Provides
    @ApplicationScope
    fun provideHistoryRepository(dao: HistoryDao) = HistoryRepository(dao)

}