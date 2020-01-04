package com.example.demo

import com.example.demo.configuration.CustomUnitTest
import com.example.demo.testcase.UserFactory
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import org.springframework.web.reactive.function.BodyInserters

@CustomUnitTest
class AccountUnitTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun sut_add_account() {
        val request = UserFactory.testCaseForCreateAccountRequest()
        //Act
        val testResponse = webTestClient.post().uri("/api/user").body(
                BodyInserters.fromValue(
                        UserFactory.testCaseForCreateAccountRequest()
                )
        ).exchange();
        //Assert
        testResponse.expectStatus().is2xxSuccessful.returnResult<Void>()
        webTestClient.get().uri("/api/user?name=${request.name}&phone=${request.phone.toString()}")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.name").isEqualTo(request.name)
                .jsonPath("$.phone").isEqualTo(request.phone.toString())
                .jsonPath("$.created").isNotEmpty
                .jsonPath("$.updated").isNotEmpty
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
    fun sut_duplicated_create_account() {
        //Arrange
        webTestClient.post().uri("/api/user").body(
                BodyInserters.fromValue(
                        UserFactory.testCaseForCreateAccountRequest()
                )
        )
        //Act
        val result = webTestClient.post().uri("/api/user").body(
                BodyInserters.fromValue(
                        UserFactory.testCaseForCreateAccountRequest()
                )
        ).exchange()
        //Assert
        result.expectStatus().is4xxClientError
    }
}