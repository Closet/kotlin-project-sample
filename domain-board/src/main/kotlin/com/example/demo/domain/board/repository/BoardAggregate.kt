package com.example.demo.domain.board.repository

import com.example.demo.commons.dto.Board
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
class BoardAggregate(
        @Id val id: String = UUID.randomUUID().toString(),
        var title: String,

        @Column(length = 2000, columnDefinition = "TEXT")
        var content: String,

        @Column(columnDefinition = "VARCHAR(100)")
        var password: String,

        @Column(columnDefinition = "VARCHAR(100)")
        val issuerId: String,

        @CreationTimestamp
        val created: LocalDateTime? = null,

        @UpdateTimestamp
        val updated: LocalDateTime? = null
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