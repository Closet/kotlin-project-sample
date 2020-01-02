package com.example.demo.domain.board

import com.example.demo.commons.dto.Board
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class BoardAggregate private constructor(
        @Id val id: String = UUID.randomUUID().toString(),
        var title: String,

        @Column(length = 2000, columnDefinition = "TEXT")
        var content: String,

        @Column(columnDefinition = "VARCHAR(100)")
        var password: String,

        @Column(columnDefinition = "VARCHAR(100)")
        val issuerId: String
) {
    companion object {
        fun ofRequest(createRequest: Board.BoardCreateRequest): BoardAggregate =
                BoardAggregate(
                        title = createRequest.title,
                        issuerId = createRequest.issuerId,
                        content = createRequest.content,
                        password = createRequest.password
                )
    }

    fun change(request: Board.ChangePasswordRequest) {
        password = request.password
    }

    fun change(request: Board.ChangeBoardRequest) {
        title = request.title
        content = request.content
    }
}