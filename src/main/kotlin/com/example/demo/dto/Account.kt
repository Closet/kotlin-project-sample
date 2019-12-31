package com.example.demo.dto

data class JoinAccountRequest(
        val name: String,
        val address: String,
        val phone: String
)


data class SearchAccountRequest(
        val name: String,
        val phone: String
)