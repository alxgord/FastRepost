package com.artto.fastrepost.data.network

import io.reactivex.Observable
import retrofit2.Call

interface IRxCallAdapter {

    fun <T> observableCall(call: Call<T>): Observable<T>
}