package com.example.demo.service

import com.example.demo.domain.LogHistoryRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class NotificationService(
        val logHistoryRepository: LogHistoryRepository
) {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    /*
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION, classes = [UserEvent::class])
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    fun eventSubScribe(userEvent: UserEvent) {
        log.warn("이벤트 발생")
        logHistoryRepository.save(UserChangeLogHistory(content = userEvent.type.name))
    }

     */
}