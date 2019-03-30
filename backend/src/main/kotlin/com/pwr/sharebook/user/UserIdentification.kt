package com.pwr.sharebook.user

data class UserIdentification(val id: Long, val email: String) {

    companion object {
        fun fromEntity(user: UserEntity): UserIdentification {
            require(user.id != null) { "User id cannot be null!" }
            require(user.email != null) { "User email cannot be null!" }
            return UserIdentification(user.id!!, user.email!!)
        }
    }

}
