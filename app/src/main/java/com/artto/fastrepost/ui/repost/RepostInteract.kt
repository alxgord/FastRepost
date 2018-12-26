package com.artto.fastrepost.ui.repost

import com.artto.fastrepost.data.clipboard.ClipboardRepository
import com.artto.fastrepost.data.database.history.HistoryEntity
import com.artto.fastrepost.data.database.history.HistoryRepository
import com.artto.fastrepost.data.instagram.InstagramRepository
import com.artto.fastrepost.data.instagram.api.InstagramApiConstants
import com.artto.fastrepost.data.instagram.response.InstagramPostContentItem
import com.artto.fastrepost.data.instagram.response.InstagramUserPost
import com.artto.fastrepost.data.properties.PropertiesManager
import com.artto.fastrepost.data.storage.StorageManager
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class RepostInteract(private val clipboardRepository: ClipboardRepository,
                     private val storageManager: StorageManager,
                     private val historyRepository: HistoryRepository,
                     private val instagramRepository: InstagramRepository,
                     private val propertiesManager: PropertiesManager) {

    fun setClipText(text: String): Completable =
            clipboardRepository.setClipText("FastRepost", text)

    fun newPostUrl(): Observable<String> =
            clipboardRepository.clipChangedListener()
                    .map { it.getItemAt(0).text.toString() }
                    .checkForPostUrl()

    fun getPostUrl(): Observable<String> =
            clipboardRepository.getCipText()
                    .checkForPostUrl()

    fun getPost(shortCode: String): Single<InstagramUserPost> =
            instagramRepository.getPost(shortCode)
                    .flatMap { storageManager.savePostContent(it) }

    fun savePostContentItem(item: InstagramPostContentItem): Single<String> =
            storageManager.saveToExternal(item)
                    .map { it.storageUrl }

    fun addToHistory(shortCode: String, position: Int): Completable =
            historyRepository.insert(HistoryEntity(shortCode = shortCode, mediaPosition = position))
                    .ignoreElement()

    fun getProperties() = propertiesManager.properties

    private fun Observable<String>.checkForPostUrl(): Observable<String> = this
            .filter { it.contains(InstagramApiConstants.POST_URL) }
            .map { it.substringAfter("/p/").substringBefore("/") }
}