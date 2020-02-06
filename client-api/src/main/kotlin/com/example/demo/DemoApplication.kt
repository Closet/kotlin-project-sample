package com.example.demo

import com.example.demo.domain.account.AccountConfiguration
import com.example.demo.domain.board.BoardConfiguration
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class DemoApplication{
}

@Configuration
@EnableAutoConfiguration
class WebContextConfiguration

fun main(args: Array<String>) {
    SpringApplicationBuilder().sources(DemoApplication::class.java).web(WebApplicationType.SERVLET)
            .child(BoardConfiguration::class.java).web(WebApplicationType.NONE)
            .sibling(AccountConfiguration::class.java).web(WebApplicationType.NONE)
            .sibling(WebContextConfiguration::class.java)
            .build()
            .run(*args)

}
