package com.artto.fastrepost.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.artto.fastrepost.data.database.history.HistoryDao
import com.artto.fastrepost.data.database.history.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1)
@TypeConverters(DatabaseTimeConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

}