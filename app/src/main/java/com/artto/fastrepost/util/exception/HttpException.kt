package com.artto.fastrepost.util.exception

import okhttp3.ResponseBody

class HttpException(val code: Int,
                    val responseMsg: String,
                    val errorBody: ResponseBody?) : Exception("code=$code; message=$responseMsg") {

    constructor(code: Int, responseMsg: String) : this(code, responseMsg, null)

}