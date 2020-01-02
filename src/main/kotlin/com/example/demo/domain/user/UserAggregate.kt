package com.example.demo.domain.user

import com.example.demo.commons.dto.Account
import com.example.demo.commons.dto.Account.CreateAccountRequest
import com.example.demo.commons.vo.DateMetaMappedSuperClass
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class UserAggregate(
        @Id val id: String =
                UUID.randomUUID().toString().replace("-", ""),

        var name: String = "",

        @Column(unique = true)
        var phone: String = "",

        var address: String = ""
) : DateMetaMappedSuperClass() {
    companion object {
        fun fromDto(accountRequest: CreateAccountRequest) = UserAggregate(
                name = accountRequest.name,
                phone = accountRequest.phone.toString(),
                address = accountRequest.address
        )
    }

    fun update(updateAccountRequest: Account.UpdateAccountRequest) {
        updateAccountRequest.phone?.let {
            this.phone = it.toString()
        }
        updateAccountRequest.address?.let {
            this.address = it.toString()
        }
    }
}