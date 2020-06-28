package com.example.demo.domain.user

import com.example.demo.commons.dto.Account
import com.example.demo.commons.vo.DateMetaMappedSuperClass
import com.example.demo.commons.vo.MobileNumber
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
class UserAggregate(
    @Id val id: String =
        UUID.randomUUID().toString().replace("-", ""),

    var name: String,

    @Column(unique = true)
    var phone: MobileNumber,

    var address: String
) : DateMetaMappedSuperClass() {
    fun update(updateAccountRequest: Account.UpdateAccountRequest) {
        updateAccountRequest.phone?.let {
            this.phone = it
        }
        updateAccountRequest.address?.let {
            this.address = it
        }
    }
}