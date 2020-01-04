@file:Suppress("NonAsciiCharacters")

package com.example.demo.domain.board

import com.example.demo.commons.dto.Board
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class BoardAggregateUnitTest(
) {
    @Test
    fun `게시판 보드 생성 유닛테스트`() {
        //Arrange
        val requestCreateBoard = Factory.defaultBoardCreateRequest

        //Act
        val createdBoard = BoardAggregate.ofRequest(requestCreateBoard)

        //Assert
        Assertions.assertThat(requestCreateBoard.title)
                .isEqualTo(createdBoard.title)
        Assertions.assertThat(requestCreateBoard.issuerId)
                .isEqualTo(createdBoard.issuerId)
        Assertions.assertThat(requestCreateBoard.content)
                .isEqualTo(createdBoard.content)
        Assertions.assertThat(requestCreateBoard.password)
                .isEqualTo(createdBoard.password)

    }

    @Test
    fun `게시판 보드 패스워드 변경 테스트`() {
        //Arrange
        val requestCreateBoard = Factory.defaultBoardCreateRequest
        val requestChangePasswordRequest = Factory.defaultBoardChangePasswordRequest

        //Act
        val createdBoard = BoardAggregate.ofRequest(requestCreateBoard)
        createdBoard.change(requestChangePasswordRequest)
        //Assert
        Assertions.assertThat(createdBoard.password)
                .isEqualTo(requestChangePasswordRequest.password)

    }

    @Test
    fun `게시판 보드 내용 변경 테스트`() {
        //Arrange
        val requestCreateBoardRequest = Factory.defaultBoardCreateRequest
        val requestChangeBoardRequest = Factory.defaultBoardChangeRequest

        //Act
        val createdBoard = BoardAggregate
                .ofRequest(requestCreateBoardRequest)
        createdBoard.change(requestChangeBoardRequest)
        //Assert
        Assertions.assertThat(createdBoard.title)
                .isEqualTo(requestChangeBoardRequest.title)
        Assertions.assertThat(createdBoard.content)
                .isEqualTo(requestChangeBoardRequest.content)

    }


    internal object Factory {
        val defaultBoardCreateRequest = Board.BoardCreateRequest(
                title = "제목",
                issuerId = "uuid",
                content = "내용",
                password = "비밀번호"
        )
        val defaultBoardChangePasswordRequest = Board.ChangePasswordRequest(
                password = "바뀜"
        )
        val defaultBoardChangeRequest = Board.ChangeBoardRequest(
                title = "수정 타이틀",
                content = "바뀐내용"
        )
    }

}