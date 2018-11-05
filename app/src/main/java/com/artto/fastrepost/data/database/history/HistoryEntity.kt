package com.artto.fastrepost.data.database.history

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.artto.fastrepost.data.database.DatabaseConstants
import java.util.*

@Entity(tableName = DatabaseConstants.HISTORY_TABLE_NAME)
data class HistoryEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = DatabaseConstants.HISTORY_ID)
        val id: Long = 0,

        @ColumnInfo(name = DatabaseConstants.HISTORY_SHORT_CODE)
        val shortCode: String,

        @ColumnInfo(name = DatabaseConstants.HISTORY_MEDIA_POSITION)
        val mediaPosition: Int,

        @ColumnInfo(name = DatabaseConstants.HISTORY_DATE)
        val date: Date = Date()
)