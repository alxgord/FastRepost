package com.artto.fastrepost.data.database.history

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun getAll(): Flowable<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: HistoryEntity): Long

    @Delete
    fun delete(entity: HistoryEntity): Int

    @Query("DELETE FROM history")
    fun clear()

}