package com.example.demo.commons.exception

class DuplicatedException(override val message: String?,
                          override val cause: Throwable? = null)
    : RuntimeException(message, cause)
