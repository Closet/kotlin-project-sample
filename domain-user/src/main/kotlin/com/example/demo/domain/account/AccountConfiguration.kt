package com.example.demo.domain.account

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackageClasses = [AccountConfiguration::class])
public class AccountConfiguration :ApplicationRunner {
    @Throws(Exception::class)
    override fun run(args: ApplicationArguments?) {
    }
}