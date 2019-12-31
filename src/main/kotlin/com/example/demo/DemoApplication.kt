package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync(proxyTargetClass = true)
@PropertySource(value=["classpath:application.dv.properties"])
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}