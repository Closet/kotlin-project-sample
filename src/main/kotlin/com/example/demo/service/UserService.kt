package com.example.demo.service

import com.example.demo.commons.exception.DuplicatedException
import com.example.demo.commons.exception.NotFoundException
import com.example.demo.domain.user.UserAggregate
import com.example.demo.domain.user.UserRepository
import com.example.demo.dto.Account
import com.example.demo.dto.Account.*
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
        val userRepository: UserRepository
        //val applicationEventPublisher: ApplicationEventPublisher
) {
    fun joinAccount(createAccountRequest: CreateAccountRequest) {
        try {
            userRepository.saveAndFlush(UserAggregate.fromDto(createAccountRequest));
        } catch (exception: DataIntegrityViolationException) {
            throw DuplicatedException("이미 가입된 회원입니다.", exception)
        }
    }

    fun getAccount(readAccountRequest: ReadAccountRequest): Account {
        return userRepository.findByName(
                readAccountRequest.name
        ).orElseThrow {
            throw NotFoundException("계정 정보를 찾지 못했습니다.")
        }.run {
            Account.fromUserAggregate(this)
        }
    }

    fun updateAccount(updateAccountRequest: UpdateAccountRequest) {
        userRepository.findById(updateAccountRequest.id).orElseThrow {
            throw NotFoundException("계정 정보를 찾지 못했습니다.")
        }.run {
            this.update(updateAccountRequest)
        }
    }
}