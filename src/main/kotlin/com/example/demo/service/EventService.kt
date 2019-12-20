package com.example.demo.service

import org.springframework.stereotype.Service

@Service
class EventService(
        val januaryPromotionRepository: JanuaryPromotionRepository
) {
    fun createEvent() {
    }
}