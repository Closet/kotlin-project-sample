package com.example.demo.domain

import com.example.demo.commons.exception.NotFoundException
import com.example.demo.dto.JoinAccountRequest
import com.example.demo.dto.SearchAccountRequest
import com.example.demo.service.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.annotation.Transactional

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestPropertySource("classpath:application.test.properties")
@Transactional
@SpringBootTest
class UserServiceTest(
        val userService: UserService
) {
    @Test
    fun `회원가입 성공`() {
        //given
        val joinAccountRequest = UserAggregateTestFactory.defaultJoinAccountRequest()
        val searchAccountRequest = UserAggregateTestFactory.defaultSuccessSearchAccountRequest()
        //when
        userService.joinAccount(joinAccountRequest)
        val resultAccountResult = userService.getAccount(searchAccountRequest)
        //then
        Assertions.assertThat(joinAccountRequest.name).isEqualTo(resultAccountResult.name)
        Assertions.assertThat(joinAccountRequest.phone).isEqualTo(resultAccountResult.phone)
        Assertions.assertThat(joinAccountRequest.address).isEqualTo(resultAccountResult.address)
    }

    @Test
    fun `중복 회원가입 예외발생`() {
        val joinAccountRequest = UserAggregateTestFactory.defaultJoinAccountRequest()
        userService.joinAccount(joinAccountRequest)
        userService.joinAccount(joinAccountRequest)

    }

    @Test
    fun `회원을 찾지 못함`() {
        val joinAccountRequest = UserAggregateTestFactory.defaultJoinAccountRequest()
        val failureSearchRequest = UserAggregateTestFactory.defaultFailureSearchAccountRequest()
        userService.joinAccount(joinAccountRequest)
        assertThrows<NotFoundException> {
            userService.getAccount(failureSearchRequest)
        }
    }
}

internal class UserAggregateTestFactory {
    companion object {
        fun defaultJoinAccountRequest() =
                JoinAccountRequest("이필수", "수원시영통구", "01021321231")

        fun defaultSuccessSearchAccountRequest() =
                SearchAccountRequest("이필수", "01021321231")

        fun defaultFailureSearchAccountRequest() =
                SearchAccountRequest("김기기", "01022321231")
    }
}