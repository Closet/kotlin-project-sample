package com.example.demo.domain.user

import com.example.demo.dto.JoinAccountRequest
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserAggregate(
        @Id val Id: String = UUID.randomUUID().toString().replace("-", ""),
        var name: String = "",
        @Column(unique = true)
        var phone: String = "",
        var address: String = ""
) {
    companion object {
        fun create(accountRequest: JoinAccountRequest) = UserAggregate(
                name = accountRequest.name
        )
    }
}