package com.pwr.sharebook.notification

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository : JpaRepository<NotificationEntity, Long>{

    fun findAllByUserEntityId(userId: Long): List<NotificationEntity>
}
