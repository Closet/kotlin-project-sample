package com.example.demo.api

import com.example.demo.dto.JoinAccountRequest
import com.example.demo.service.NotificationService
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
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

    @PostMapping("/join")
    fun test1(@RequestBody accountRequest: JoinAccountRequest) {
        userService.joinAccount(accountRequest)
    }
}