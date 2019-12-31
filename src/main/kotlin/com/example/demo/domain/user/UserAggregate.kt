package com.example.demo.domain.user

import com.example.demo.dto.Account
import com.example.demo.dto.Account.CreateAccountRequest
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserAggregate(
        @Id val id: String = UUID.randomUUID().toString().replace("-", ""),
        var name: String = "",
        @Column(unique = true)
        var phone: String = "",
        var address: String = ""
) {
    companion object {
        fun fromDto(accountRequest: CreateAccountRequest) = UserAggregate(
                name = accountRequest.name,
                phone = accountRequest.phone,
                address = accountRequest.address
        )
    }

    fun update(updateAccountRequest: Account.UpdateAccountRequest) {
        updateAccountRequest.phone?.let {
            this.phone = it
        }
        updateAccountRequest.address?.let {
            this.address = it
        }
    }
}