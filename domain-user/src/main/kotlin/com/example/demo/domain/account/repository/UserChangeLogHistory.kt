package com.example.demo.domain.account.repository

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class UserChangeLogHistory(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val Id: Int? = null,
        val content: String = "",
        val userCommandType: UserCommandType
)