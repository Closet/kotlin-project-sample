package com.example.demo.domain.board.dto

class Board(){
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