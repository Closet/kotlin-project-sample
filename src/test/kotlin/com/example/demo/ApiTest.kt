package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.StopWatch
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

@WebFluxTest
class ApiTest() {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun nonBlocking() {
        val loopCount = 10;
        val stopWatch = StopWatch();
        val count = CountDownLatch(loopCount)
        stopWatch.start();
        IntStream.range(0, loopCount).forEach {
            webTestClient.get().uri("http://localhost:8080/test1").exchange()
                    .expectStatus().isOk
                    .expectBody(String::class.java)
                    .returnResult().let {
                        count.countDown()
                        println(it.responseBody)
                    }
        }

        count.await(10, TimeUnit.SECONDS)
        stopWatch.stop();
        println(stopWatch.totalTimeSeconds)
    }
}