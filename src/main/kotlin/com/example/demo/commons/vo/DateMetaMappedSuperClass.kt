package com.example.demo.commons.vo

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class DateMetaMappedSuperClass(
        @CreationTimestamp
        var created: LocalDateTime? = null,

        @UpdateTimestamp
        var updated: LocalDateTime? = null
)