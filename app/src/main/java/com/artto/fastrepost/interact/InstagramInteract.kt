package com.artto.fastrepost.interact

import com.artto.fastrepost.data.instagram.api.InstagramApi
import com.artto.fastrepost.data.instagram.response.InstagramUserPost
import io.reactivex.Single

class InstagramInteract(private val api: InstagramApi) {

    fun getPost(shortCode: String): Single<InstagramUserPost> =
            api.getPost(shortCode).toObservable().map { it.post }.firstOrError()
}