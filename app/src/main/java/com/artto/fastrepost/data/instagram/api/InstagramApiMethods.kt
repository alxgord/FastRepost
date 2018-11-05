package com.artto.fastrepost.data.instagram.api

import com.artto.fastrepost.data.instagram.response.GetPostInstagramResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface InstagramApiMethods {

    @GET("p/{shortcode}/?__a=1")
    fun getPost(@Path("shortcode") shortCode: String): Single<GetPostInstagramResponse>
}