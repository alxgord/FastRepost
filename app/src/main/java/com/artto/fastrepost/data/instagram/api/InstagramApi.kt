package com.artto.fastrepost.data.instagram.api

import com.artto.fastrepost.data.network.IRxCallAdapter
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call

class InstagramApi(private val apiMethods: InstagramApiMethods, private val rxCallAdapter: IRxCallAdapter) {

    fun getPost(shortCode: String) = apiMethods.getPost(shortCode).single()

    private fun <T> Call<T>.observable(): Observable<T> = rxCallAdapter.observableCall(this)

    private fun <T> Call<T>.single(): Single<T> = observable().firstOrError()

    private fun <T> Call<T>.completable(): Completable = observable().ignoreElements()
}