package com.artto.fastrepost.util.exception

import java.io.IOException

class NetworkException : IOException {

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)

}