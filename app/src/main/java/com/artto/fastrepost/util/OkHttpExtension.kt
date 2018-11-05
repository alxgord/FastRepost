package com.artto.fastrepost.util

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

fun Call.enqueue(
        onFailure: (call: Call, e: Exception) -> Unit,
        onResponse: (call: Call, response: Response) -> Unit) = enqueue(object : Callback {

    override fun onFailure(call: Call, e: IOException) = onFailure.invoke(call, e)

    override fun onResponse(call: Call, response: Response) = onResponse.invoke(call, response)

})

