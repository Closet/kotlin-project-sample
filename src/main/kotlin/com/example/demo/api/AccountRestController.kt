package com.example.demo.api

import com.example.demo.dto.Account.CreateAccountRequest
import com.example.demo.service.NotificationService
import com.example.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AccountRestController(
        private val userService: UserService,
        private val notificationService: NotificationService
) {

    @PostMapping("/user")
    fun postUser(@RequestBody accountRequest: CreateAccountRequest): ResponseEntity<Void> {
        userService.createAccount(accountRequest)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PatchMapping("/user")
    fun patchUser(@RequestBody accountRequest: CreateAccountRequest): ResponseEntity<Void> {
        userService.createAccount(accountRequest)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}