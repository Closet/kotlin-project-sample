package com.example.demo.commons.vo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

class MobileNumber private constructor(
        private val value: String
) {

    companion object {
        @JsonCreator
        fun ofNumber(value: String): MobileNumber {
            validateNumberCheck(value)
            return MobileNumber(value)
        }

        private fun validateNumberCheck(value: String) {
            if (value.length == 10 || value.length == 11) {
                val regex = """^(01[016789]{1})([0-9]{3,4})([0-9]{4})$""".toRegex()
                regex.matchEntire(value) ?: throw NumberIsNotInvalidException("전화번호가 올바르지 않습니다.")
            } else {
                throw NumberIsNotInvalidException("전화번호가 올바르지 않습니다.")
            }
        }
    }

    @JsonValue
    override fun toString(): String {
        return value
    }

    override fun equals(other: Any?): Boolean {
        return other?.run {
            other is MobileNumber
                    && (value == (this as MobileNumber).value)
        } ?: false
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    class NumberIsNotInvalidException
    (
            override val message: String?,
            override val cause: Throwable? = null
    ) : RuntimeException(message, cause)
}
