package com.example.demo.domain.account.dto

import com.example.demo.commons.vo.MobileNumber
import com.example.demo.domain.account.repository.UserAggregate
import java.time.LocalDateTime

public class Account(
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
                        phone = userAggregate.phone,
                        created = userAggregate.created,
                        updated = userAggregate.updated
                )
    }


    class CreateAccountRequest(
            val name: String,
            val address: String,
            val phone: MobileNumber
    ) {
        fun toUserAggregate(): UserAggregate {
            return UserAggregate(
                    name = name,
                    phone = phone,
                    address = address
            )

        }

    }

    class UpdateAccountRequest(
            val id: String,
            val address: String? = null,
            val phone: MobileNumber? = null
    )

    class ReadAccountRequest(
            val name: String,
            val phone: MobileNumber
    )
}
