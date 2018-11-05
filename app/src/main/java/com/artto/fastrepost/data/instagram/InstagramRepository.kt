package com.artto.fastrepost.data.instagram

import com.artto.fastrepost.data.instagram.api.InstagramApi
import com.artto.fastrepost.data.instagram.response.InstagramUserPost
import io.reactivex.Single

class InstagramRepository(private val api: InstagramApi) {

    fun getPost(shortCode: String): Single<InstagramUserPost> = api.getPost(shortCode).map { it.post }
}