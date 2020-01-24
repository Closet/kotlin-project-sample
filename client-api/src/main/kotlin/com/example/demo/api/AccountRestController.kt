package com.example.demo.api

import com.example.demo.domain.account.dto.Account
import com.example.demo.domain.account.repository.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AccountRestController(
        private val userService: UserService

) {

    @PostMapping("/user")
    fun postUser(@RequestBody accountRequest: Account.CreateAccountRequest): ResponseEntity<Void> {
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
}