package com.example.demo.domain.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class UserChangeLogHistory(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val Id: Int? = null,
        val content: String = "",
        val userCommandType: UserCommandType
) {
}