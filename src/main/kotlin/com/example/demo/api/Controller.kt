package com.example.demo.api

import com.example.demo.dto.Account.CreateAccountRequest
import com.example.demo.service.NotificationService
import com.example.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@RestController
class Controller(
        private val userService: UserService,
        private val notificationService: NotificationService
) {
    @GetMapping("/test")
    fun test() = WebClient.create()
            .get().uri("https://www.baeldung.com/").retrieve().bodyToMono<String>().log()

    @PostMapping("/user")
    fun postUser(@RequestBody accountRequest: CreateAccountRequest): ResponseEntity<Void> {
        userService.joinAccount(accountRequest)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PatchMapping("/user")
    fun patchUser(@RequestBody accountRequest: CreateAccountRequest): ResponseEntity<Void> {
        userService.joinAccount(accountRequest)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}