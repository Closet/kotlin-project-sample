package com.example.demo.commons.vo

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable

class MobileNumber(private var value: String) : Serializable {

    init {
        value = value.replace("-", "").also {
            validateNumberCheck(it)
        }
    }

    companion object {
        private fun validateNumberCheck(value: String) {
            if (value.length == 10 || value.length == 11) {
                val regex = """^(01[016789]{1})([0-9]{3,4})([0-9]{4})$""".toRegex()
                regex.matchEntire(value) ?: throw NumberIsNotInvalidException("invalid mobile phone number")
            } else {
                throw NumberIsNotInvalidException("invalid mobile phone number")
            }
        }
    }

    @JsonValue
    override fun toString(): String = this.value


    override fun equals(other: Any?): Boolean {
        return other?.run {
            value == other
        } ?: false
    }

    class NumberIsNotInvalidException
    (
            override val message: String?,
            override val cause: Throwable? = null
    ) : RuntimeException(message, cause)
}
