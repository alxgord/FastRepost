package com.artto.fastrepost.data.instagram.api

class InstagramApi(private val apiMethods: InstagramApiMethods) {

    fun getPost(shortCode: String) = apiMethods.getPost(shortCode)
}