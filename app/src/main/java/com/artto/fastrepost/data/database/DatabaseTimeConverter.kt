package com.artto.fastrepost.data.database

import android.arch.persistence.room.TypeConverter
import java.util.*

class DatabaseTimeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        value ?: return null
        return Date(value)
    }

    @TypeConverter
    fun toTimestamp(value: Date?): Long? {
        value ?: return null
        return value.time
    }
}