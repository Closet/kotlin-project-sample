package com.example.demo.domain.account.repository

import com.example.demo.commons.exception.DuplicatedException
import com.example.demo.commons.exception.NotFoundException
import com.example.demo.domain.account.dto.Account
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
public class UserService(
        private val userRepository: UserRepository
        //todo 이벤트 발행 구현해야함
        //val applicationEventPublisher: ApplicationEventPublisher
) {
    fun createAccount(createAccountRequest: Account.CreateAccountRequest) {
        try {
            userRepository.saveAndFlush(createAccountRequest.toUserAggregate())
        } catch (exception: DataIntegrityViolationException) {
            throw DuplicatedException("이미 가입된 회원입니다.", exception)
        }
    }

    fun readAccount(readAccountRequest: Account.ReadAccountRequest): Account {
        return userRepository.findByName(
                readAccountRequest.name
        ).orElseThrow {
            throw NotFoundException("계정 정보를 찾지 못했습니다.")
        }.run {
            Account.fromUserAggregate(this)
        }
    }

    fun updateAccount(updateAccountRequest: Account.UpdateAccountRequest) {
        userRepository.findById(updateAccountRequest.id).orElseThrow {
            throw NotFoundException("계정 정보를 찾지 못했습니다.")
        }.run {
            this.update(updateAccountRequest)
        }
    }
}