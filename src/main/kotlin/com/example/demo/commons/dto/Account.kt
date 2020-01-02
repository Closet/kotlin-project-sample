package com.example.demo.commons.dto

import com.example.demo.commons.vo.MobileNumber
import com.example.demo.domain.user.UserAggregate
import java.time.LocalDateTime

class Account(
        val id: String,
        val name: String,
        val phone: MobileNumber,
        val address: String,
        val created: LocalDateTime?,
        val updated: LocalDateTime?
) {
    companion object {
        fun fromUserAggregate(userAggregate: UserAggregate) =
                Account(
                        id = userAggregate.id,
                        address = userAggregate.address,
                        name = userAggregate.name,
                        phone = MobileNumber.ofNumber(userAggregate.phone),
                        created = userAggregate.created,
                        updated = userAggregate.updated
                )
    }


    data class CreateAccountRequest(
            val name: String,
            val address: String,
            val phone: MobileNumber
    )

    data class UpdateAccountRequest(
            val id: String,
            val address: String? = null,
            val phone: MobileNumber? = null
    )

    data class ReadAccountRequest(
            val name: String,
            val phone: MobileNumber
    )
}
