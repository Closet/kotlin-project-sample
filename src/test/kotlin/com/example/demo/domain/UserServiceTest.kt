@file:Suppress("NonAsciiCharacters")

package com.example.demo.domain

import com.example.demo.commons.exception.DuplicatedException
import com.example.demo.commons.exception.NotFoundException
import com.example.demo.configuration.ServiceWithDatabaseTest
import com.example.demo.domain.user.UserAggregate
import com.example.demo.domain.user.UserRepository
import com.example.demo.dto.Account.*
import com.example.demo.service.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@ServiceWithDatabaseTest
class UserServiceTest(
        private val userRepository: UserRepository
) {
    private val userService: UserService = UserService(userRepository)

    @Test
    fun `CREATE_회원가입 성공`() {
        //Arrange
        val joinAccountRequest = UserAggregateTestFactory.defaultJoinAccountRequest()
        val searchAccountRequest = UserAggregateTestFactory.defaultSuccessSearchAccountRequest()

        //Act
        userService.joinAccount(joinAccountRequest)
        val resultAccountResult = userService.getAccount(searchAccountRequest)

        //Assert
        Assertions.assertThat(joinAccountRequest.name).isEqualTo(resultAccountResult.name)
        Assertions.assertThat(joinAccountRequest.phone).isEqualTo(resultAccountResult.phone)
        Assertions.assertThat(joinAccountRequest.address).isEqualTo(resultAccountResult.address)
    }

    @Test
    fun `CREATE_중복 회원가입 예외발생`() {
        //Arrange
        val joinAccountRequest = UserAggregateTestFactory.defaultJoinAccountRequest()
        userService.joinAccount(joinAccountRequest)

        //Act && Assert
        assertThrows<DuplicatedException> {
            userService.joinAccount(joinAccountRequest)
        }
    }

    @Test
    fun `READ_회원을 찾지 못함`() {
        //Arrange
        val failureSearchRequest = UserAggregateTestFactory.defaultFailureSearchAccountRequest()

        //Act && Assert
        assertThrows<NotFoundException> {
            userService.getAccount(failureSearchRequest)
        }
    }


    @Test
    fun `UPDATE_회원정보 찾지못함`() {
        //Arrange
        val updateAccountRequest = UserAggregateTestFactory.defaultUpdateAllRequest()

        //Act && Assert
        assertThrows<NotFoundException> {
            userService.updateAccount(updateAccountRequest)
        }
    }

    @Test
    fun `UPDATE_회원정보 수정됨`() {
        //Arrange
        userRepository.saveAndFlush(UserAggregateTestFactory.defaultUserAggregate())
        val updateRequest = UserAggregateTestFactory.defaultUpdateAllRequest()
        val searchRequest = UserAggregateTestFactory.defaultSuccessSearchAccountRequest()

        //Act
        userService.updateAccount(updateRequest)
        userRepository.flush()

        //Assert
        val result = userService.getAccount(searchRequest)
        Assertions.assertThat(result.address).isEqualTo(updateRequest.address)
        Assertions.assertThat(result.phone).isEqualTo(updateRequest.phone)

    }
}

internal class UserAggregateTestFactory {
    companion object {

        fun defaultJoinAccountRequest() =
                CreateAccountRequest("이필수", "수원시영통구", "01021321231")

        fun defaultUpdateAllRequest() =
                UpdateAccountRequest(id = "1234", address = "수원시장안구", phone = "01000203455")

        fun defaultUserAggregate() =
                UserAggregate(id = "1234", name = "이필수", address = "수원시영통구", phone = "01000000000")

        fun defaultSuccessSearchAccountRequest() =
                ReadAccountRequest("이필수", "01021321231")

        fun defaultFailureSearchAccountRequest() =
                ReadAccountRequest("김기기", "01022321231")
    }
}