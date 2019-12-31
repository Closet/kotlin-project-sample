package com.example.demo.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository : JpaRepository<UserAggregate, String> {
    fun findByPhoneAndName(phone: String, name: String):Optional<UserAggregate>

}