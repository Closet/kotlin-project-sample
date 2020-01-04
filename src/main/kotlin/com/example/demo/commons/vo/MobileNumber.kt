package com.example.demo.commons.vo

import com.fasterxml.jackson.annotation.JsonValue
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
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
                regex.matchEntire(value) ?: throw NumberIsNotInvalidException("전화번호가 올바르지 않습니다.")
            } else {
                throw NumberIsNotInvalidException("invalid mobile phone number")
            }
        }
    }

    @JsonValue
    override fun toString(): String = this.value


    override fun equals(other: Any?): Boolean {
        return other?.run {
            value == other || other is MobileNumber
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
