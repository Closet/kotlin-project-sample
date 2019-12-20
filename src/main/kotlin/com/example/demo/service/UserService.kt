package com.example.demo.service

import com.example.demo.dto.JoinAccountRequest
import com.example.demo.event.UserEvent
import com.example.demo.event.UserEventType
import com.example.demo.domain.user.User
import com.example.demo.domain.user.UserRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import kotlin.random.Random

@Service
@Transactional
class UserService(
        val userRepository: UserRepository,
        val applicationEventPublisher: ApplicationEventPublisher
) {


    fun joinAccount(joinAccountRequest: JoinAccountRequest) {
        val user = userRepository.save(User.create(joinAccountRequest))
        try {
            if (Random.nextInt() % 2 == 1) {
            } else {
                throw RuntimeException("에러발생")
            }
        } catch (exception: RuntimeException) {
            applicationEventPublisher.publishEvent(UserEvent(user, UserEventType.FAILED))
            throw exception;
        }
        applicationEventPublisher.publishEvent(UserEvent(user, UserEventType.CREATED))
    }

}