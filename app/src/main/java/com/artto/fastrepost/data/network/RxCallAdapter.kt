package com.artto.fastrepost.data.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RxCallAdapter : IRxCallAdapter {

    override fun <T> observableCall(call: Call<T>): Observable<T> = Observable.create { emitter ->
        emitter.setCancellable { call.cancel() }
        call.enqueue(object : Callback<T> {

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (!emitter.isDisposed) emitter.onError(NetworkException(t))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let { emitter.onNext(it) }
                    emitter.onComplete()
                } else {
                    val exception = HttpException(response.code(), response.message(), response.errorBody())
                    if (!emitter.isDisposed) emitter.onError(exception)
                }
            }

        })
    }
}