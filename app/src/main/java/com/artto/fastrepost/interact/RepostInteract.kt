package com.artto.fastrepost.interact

import com.artto.fastrepost.data.instagram.api.InstagramApiConstants
import com.artto.fastrepost.data.instagram.response.InstagramUserPost
import io.reactivex.Observable
import io.reactivex.Single

class RepostInteract(private val clipboardInteract: ClipboardInteract,
                     private val instagramInteract: InstagramInteract) {

    fun newPostUrl(): Observable<String> =
            clipboardInteract
                    .clipChangedListener()
                    .map { it.getItemAt(0).text.toString() }
                    .checkForPostUrl()

    fun getPostUrl() =
            clipboardInteract
                    .getCipText()
                    .checkForPostUrl().firstOrError()

    fun getPost(shortCode: String): Single<InstagramUserPost> =
            instagramInteract
                    .getPost(shortCode)

    private fun Observable<String>.checkForPostUrl(): Observable<String> = this
            .filter { it.contains(InstagramApiConstants.POST_URL) }
            .map { it.substringAfter("/p/").substringBefore("/") }
}