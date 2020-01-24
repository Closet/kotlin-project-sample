package com.example.demo.account

import com.example.demo.commons.vo.MobileNumber
import com.example.demo.configuration.CustomUnitTest
import com.example.demo.domain.account.dto.Account
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import org.springframework.web.reactive.function.BodyInserters
import java.util.*

@CustomUnitTest
class AccountUnitTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun sut_find_non_exist_account() {
        //Arrange
        val emptyUserName = "없는 유저"
        val emptyUserPhone = "01922222224"
        //Act
        webTestClient.get().uri("/api/user?name=${emptyUserName}&phone=${emptyUserPhone}")
                .exchange()
                .expectStatus().isNotFound
    }

    @Test
    fun sut_add_account() {
        //Arrange
        val request = UserFixture.testCaseForCreateAccountRequest()
        //Act
        val testResponse = webTestClient.post().uri("/api/user").body(
                BodyInserters.fromValue(
                        UserFixture.testCaseForCreateAccountRequest()
                )
        ).exchange()
        //Assert
        testResponse.expectStatus().is2xxSuccessful.returnResult<Void>()
        webTestClient.get().uri("/api/user?name=${request.name}&phone=${request.phone}")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.name").isEqualTo(request.name)
                .jsonPath("$.address").isEqualTo(request.address)
                .jsonPath("$.phone").isEqualTo(request.phone.toString())
                .jsonPath("$.created").exists()
                .jsonPath("$.updated").exists()
    }

    @Test
    fun sut_add_wrong_number_account() {
        val jsonRequest = """{
                    "name":"테스트",
                    "address":"주소",
                    "phone":"02-222-2222"
                }""".trimIndent()


        webTestClient.post().uri("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonRequest)
                .exchange()
                .expectStatus().is4xxClientError
                .expectBody().jsonPath("$.message")
                .value(containsString("invalid mobile phone number"))


    }

    @Test
    fun sut_change_account_address_information() {
        //Arrange
        val createRequest = UserFixture.testCaseForCreateAccountRequest()
        webTestClient.post().uri("/api/user").body(
                BodyInserters.fromValue(
                        createRequest
                )
        )
        val accountId = webTestClient.get().uri("/api/user?name=${createRequest.name}&phone=${createRequest.phone}")
                .exchange()
                .expectStatus().is2xxSuccessful
                .returnResult(Account::class.java)
                .responseBody.blockFirst()!!.id


        val updateRequest = Account.UpdateAccountRequest(
                accountId,
                "대전",
                MobileNumber("010-1234-2345")
        )

        //Act
        webTestClient.patch().uri("/api/user")
                .body(BodyInserters.fromValue(updateRequest))
                .exchange()
                .expectStatus().is2xxSuccessful

        //Assert
        webTestClient.get().uri("/api/user?name=${createRequest.name}&phone=${updateRequest.phone}")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.name").isEqualTo(createRequest.name)
                .jsonPath("$.address").isEqualTo(updateRequest.address!!)
                .jsonPath("$.phone").isEqualTo(updateRequest.phone!!)
                .jsonPath("$.created").exists()
                .jsonPath("$.updated").exists()

    }

    @Test
    fun sut_change_unknown_account_address_information() {
        //Arrange
        val updateRequest = Account.UpdateAccountRequest(
                UUID.randomUUID().toString().replace("-", ""),
                "대전",
                MobileNumber("010-1234-2345")
        )

        //Act
        webTestClient.patch().uri("/api/user")
                .body(BodyInserters.fromValue(updateRequest))
                .exchange()
                .expectStatus().isNotFound

    }

    @Test
    fun sut_duplicated_create_account() {
        //Arrange
        webTestClient.post().uri("/api/user").body(
                BodyInserters.fromValue(
                        UserFixture.testCaseForCreateAccountRequest()
                )
        )
        //Act
        val result = webTestClient.post().uri("/api/user").body(
                BodyInserters.fromValue(
                        UserFixture.testCaseForCreateAccountRequest()
                )
        ).exchange()
        //Assert
        result.expectStatus().is4xxClientError
    }
}