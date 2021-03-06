package com.pwr.sharebook.notification

import com.pwr.sharebook.common.exceptions.CannotFindIdException
import com.pwr.sharebook.environment.EnvironmentService
import com.pwr.sharebook.environment.FRONTEND_ORIGIN_KEY
import com.pwr.sharebook.event.EventPublisher
import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.notification.event.NewPostInGroupEvent
import com.pwr.sharebook.notification.event.UserAddedToGroupEvent
import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.user.UserService
import com.pwr.sharebook.websocket.UpdateNotificationsWebsocketEvent
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
@Transactional
class NotificationService
@Autowired
constructor(
        private val notificationRepository: NotificationRepository,
        private val userService: UserService,
        private val groupService: GroupService,
        private val environmentService: EnvironmentService,
        private val eventPublisher: EventPublisher
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun save(event: NewPostInGroupEvent) {
        val userCreator = userService.findById(event.userCreatorId) ?: throw CannotFindIdException()
        val group = groupService.findById(event.groupId) ?: throw CannotFindIdException()
        val notificationUrl = "${environmentService.getString(FRONTEND_ORIGIN_KEY)}/groups/${group.id}"

        val usersForGroup = groupService.findUsersForGroup(event.groupId)
//                .filter { user -> true && user.id != userCreator.id } //TODO test only

        val notifications = usersForGroup
                .map { user ->
                    NotificationEntity(
                            null,
                            "Nowy wpis w grupie ${group.name}!",
                            "Użytkownik ${userCreator.firstName} ${userCreator.lastName} dodał nowy wpis",
                            notificationUrl,
                            LocalDateTime.now(),
                            false,
                            UserEntity(user.id)
                    )
                }

        logger.info("Inserting {} notifications for group {}", notifications.size, group.id)
        notificationRepository.saveAll(notifications)

        updateNotificationsMessage(usersForGroup.map { it.email })
    }

    fun save(event: UserAddedToGroupEvent) {
        val userCreator = userService.findById(event.userCreated) ?: throw CannotFindIdException()
        val invitedUser = userService.findByEmail(event.userAddedEmail) ?: throw CannotFindIdException()
        val group = groupService.findById(event.groupId) ?: throw CannotFindIdException()

        val notificationUrl = "${environmentService.getString(FRONTEND_ORIGIN_KEY)}/groups/${group.id}"

        val notification =
                NotificationEntity(
                        null,
                        "Zostałeś zaproszony do grupy ${group.name}!",
                        "Użytkownik ${userCreator.firstName} ${userCreator.lastName} dodał Cię do groupy ${group.name}",
                        notificationUrl,
                        LocalDateTime.now(),
                        false,
                        invitedUser)

        logger.info("Inserting invitation to the group {} for user {}", group.id, invitedUser.email)
        notificationRepository.save(notification)

        updateNotificationsMessage(listOf(invitedUser.email!!))
    }

    fun findAllForUser(userId: Long): List<NotificationDto> =
            notificationRepository.findAllByUserEntityId(userId)
                    .map { n -> NotificationDto.fromEntity(n) }


    private fun updateNotificationsMessage(usersNames: List<String>) {
        eventPublisher.publish(UpdateNotificationsWebsocketEvent(this, usersNames))
    }

    fun visitNotification(id: Long) {
        val notification: NotificationEntity = notificationRepository.findByIdOrNull(id) ?: throw CannotFindIdException()
        notification.visited = true
        notificationRepository.save(notification)
    }
}
