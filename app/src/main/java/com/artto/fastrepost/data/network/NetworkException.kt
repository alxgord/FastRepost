package com.artto.fastrepost.data.network

import java.io.IOException

class NetworkException : IOException {

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)

}