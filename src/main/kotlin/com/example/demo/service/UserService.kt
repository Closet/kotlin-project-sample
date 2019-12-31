package com.example.demo.service

import com.example.demo.commons.exception.NotFoundException
import com.example.demo.domain.user.UserAggregate
import com.example.demo.domain.user.UserRepository
import com.example.demo.dto.JoinAccountRequest
import com.example.demo.dto.SearchAccountRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
        val userRepository: UserRepository
        //val applicationEventPublisher: ApplicationEventPublisher
) {


    fun joinAccount(joinAccountRequest: JoinAccountRequest) {
        val user = userRepository.save(UserAggregate.create(joinAccountRequest))
    }

    fun getAccount(searchAccountRequest: SearchAccountRequest): UserAggregate {
        return userRepository.findByPhoneAndName(
                searchAccountRequest.phone,
                searchAccountRequest.name
        ).orElseThrow {
            throw NotFoundException("계정 정보를 찾지 못했습니다.")
        }
    }
}