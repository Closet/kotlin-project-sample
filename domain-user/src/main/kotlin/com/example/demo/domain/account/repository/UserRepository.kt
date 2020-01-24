package com.example.demo.domain.account.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository : JpaRepository<UserAggregate, String> {
    fun findByName(name: String):Optional<UserAggregate>

}