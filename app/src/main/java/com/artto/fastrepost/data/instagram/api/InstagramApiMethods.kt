package com.artto.fastrepost.data.instagram.api

import com.artto.fastrepost.data.instagram.response.GetPostInstagramResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface InstagramApiMethods {

    @GET("p/{shortcode}/?__a=1")
    fun getPost(@Path("shortcode") shortCode: String): Call<GetPostInstagramResponse>
}