package com.artto.fastrepost.data.database.history

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class HistoryRepository(private val dao: HistoryDao) {

    fun getAll(): Flowable<List<HistoryEntity>> =
            dao.getAll()

    fun insert(entity: HistoryEntity): Single<Long> =
            Single.fromCallable { dao.insert(entity) }

    fun delete(entity: HistoryEntity): Single<Int> =
            Single.fromCallable { dao.delete(entity) }

    fun clear(): Completable = Completable.fromCallable { dao.clear() }
}