package com.example.demo.domain.user

import com.example.demo.dto.JoinAccountRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val Id: Int? = null,
        val name: String = ""
) {
    companion object {
        fun create(accountRequest: JoinAccountRequest) = User(
                name = accountRequest.name
        )
    }
}

