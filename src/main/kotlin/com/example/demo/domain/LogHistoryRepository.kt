package com.example.demo.domain

import com.example.demo.domain.user.UserChangeLogHistory
import org.springframework.data.jpa.repository.JpaRepository

interface LogHistoryRepository : JpaRepository<UserChangeLogHistory, Int>