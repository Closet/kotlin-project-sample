package com.example.demo.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@RestController
@RequestMapping("/api")
class PublicRestController {
    @GetMapping("/ping")
    fun ping() = ResponseEntity.status(HttpStatus.OK).body("pong")

    @GetMapping("/test")
    fun test() = WebClient.create()
            .get().uri("https://www.naver.com/").retrieve().bodyToMono<String>().log()
}