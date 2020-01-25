package com.example.demo.domain.account.repository

import com.example.demo.commons.vo.MobileNumber
import com.example.demo.domain.account.dto.Account
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class UserAggregate(
        @Id val id: String =
                UUID.randomUUID().toString().replace("-", ""),

        var name: String,

        @Column(unique = true)
        var phone: MobileNumber,

        var address: String,
        @CreationTimestamp
        val created: LocalDateTime? = null,

        @UpdateTimestamp
        val updated: LocalDateTime? = null
) {
    fun update(updateAccountRequest: Account.UpdateAccountRequest) {
        updateAccountRequest.phone?.let {
            this.phone = it
        }
        updateAccountRequest.address?.let {
            this.address = it
        }
    }
}
