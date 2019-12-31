package com.example.demo.dto

import com.example.demo.domain.user.UserAggregate

class Account(
        val id: String,
        val name: String,
        val phone: String,
        val address: String
) {
    companion object {
        fun fromUserAggregate(userAggregate: UserAggregate) =
                Account(
                        id = userAggregate.id,
                        address = userAggregate.address,
                        name = userAggregate.name,
                        phone = userAggregate.phone
                )
    }


    data class CreateAccountRequest(
            val name: String,
            val address: String,
            val phone: String
    )

    data class UpdateAccountRequest(
            val id: String,
            val address: String? = null,
            val phone: String? = null
    )

    data class ReadAccountRequest(
            val name: String,
            val phone: String
    )
}
