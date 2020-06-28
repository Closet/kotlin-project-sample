package com.example.demo.commons.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException(
    override val message: String?,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)
