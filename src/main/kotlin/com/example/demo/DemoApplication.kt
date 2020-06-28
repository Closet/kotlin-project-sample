package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@PropertySource("classpath:application-test.properties")
@EnableJpaRepositories(basePackages = ["com.example.demo.domain"])
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}