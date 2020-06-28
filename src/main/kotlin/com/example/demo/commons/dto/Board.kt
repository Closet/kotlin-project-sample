package com.example.demo.commons.dto

import com.example.demo.commons.vo.DateMetaMappedSuperClass

class Board() : DateMetaMappedSuperClass() {
    data class BoardCreateRequest(
        val issuerId: String,
        val title: String,
        val content: String,
        val password: String
    )

    data class ChangePasswordRequest(
        val password: String
    )

    data class ChangeBoardRequest(
        val content: String,
        val title: String
    )
}