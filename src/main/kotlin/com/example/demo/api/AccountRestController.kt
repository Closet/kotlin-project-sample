package com.example.demo.api

import com.example.demo.commons.dto.Account
import com.example.demo.commons.dto.Account.CreateAccountRequest
import com.example.demo.service.UserService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@RestController
@RequestMapping("/api")
class
AccountRestController(
    private val userService: UserService
) {

    @PostMapping("/user")
    fun postUser(@RequestBody accountRequest: CreateAccountRequest): ResponseEntity<Void> {
        userService.createAccount(accountRequest)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @GetMapping("/user")
    fun postUser(readAccountRequest: Account.ReadAccountRequest): Account {
        return userService.readAccount(readAccountRequest)
    }

    @PatchMapping("/user")
    fun patchUser(@RequestBody accountRequest: Account.UpdateAccountRequest): ResponseEntity<Void> {
        userService.updateAccount(accountRequest)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @GetMapping("/test1")
    fun test(
        @RequestParam
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        param: LocalDateTime
    ): ZonedDateTime {
        return ZonedDateTime.of(param, ZoneId.systemDefault())
    }

    @GetMapping("/test2")
    fun test2(): LocalDateTime {
        return LocalDateTime.now();
    }

}