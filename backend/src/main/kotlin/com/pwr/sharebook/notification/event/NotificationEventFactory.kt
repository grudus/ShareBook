package com.pwr.sharebook.notification.event

import com.pwr.sharebook.group.post.AddPostRequest
import com.pwr.sharebook.group.usergroup.AddUserToGroupRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class NotificationEventFactory
@Autowired
constructor() {

    fun newPostInGroup(addPostRequest: AddPostRequest, groupId: Long, userId: Long): AbstractNotificationEvent {
        return NewPostInGroupEvent(this, addPostRequest.text, groupId, userId)
    }

    fun userAddedToGroup(addUserToGroupRequest: AddUserToGroupRequest, creatorId: Long): AbstractNotificationEvent {
        return UserAddedToGroupEvent(this, creatorId, addUserToGroupRequest.email, addUserToGroupRequest.groupId)
    }

}
