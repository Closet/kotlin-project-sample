package com.example.demo.commons.vo

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*


/*
* Embeddable에서 Auditing을 쓸때 미구현 오류가 발생
* -> 대응 방법은 @PrePersist 와 @PreUpdate를 이용하여 직접 구현
* https://hibernate.atlassian.net/browse/HHH-13235
* -> 코틀린에서 해당사항 적용 안됨.
*/
@Deprecated(level = DeprecationLevel.ERROR, message = "코틀린 작동 안되는 것으로 인한 미구현")
@EntityListeners(AuditingEntityListener::class)
@Embeddable
open class DateMeta(
    @CreationTimestamp
    @Column(insertable = false, updatable = false, nullable = false)
    var created: Date? = null,

    @UpdateTimestamp
    @Column(insertable = false, updatable = false, nullable = false)
    var updated: Date? = null
) {
    @PrePersist
    open fun prePersist() {
        this.created = Calendar.getInstance().time
    }

    @PreUpdate
    open fun preUpdate() {
        updated = Calendar.getInstance().time
    }

}