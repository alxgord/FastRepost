package com.artto.fastrepost.ui.history

import com.artto.fastrepost.data.database.history.HistoryEntity
import com.artto.fastrepost.data.database.history.HistoryRepository
import com.artto.fastrepost.data.instagram.InstagramRepository

class HistoryInteract(private val historyRepository: HistoryRepository,
                      private val instagramRepository: InstagramRepository) {

    fun getAllHistory() = historyRepository.getAll()

    fun delete(entity: HistoryEntity) = historyRepository.delete(entity)

    fun getPost(shortCode: String) = instagramRepository.getPost(shortCode)
}