package com.example.demo.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserAggregate, String> {
    fun findByName(name: String): Optional<UserAggregate>
}