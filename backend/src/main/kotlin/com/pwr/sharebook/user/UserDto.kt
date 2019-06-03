package com.pwr.sharebook.user

data class UserDto(val id: Long,
                   val email: String,
                   val firstName: String?,
                   val lastName: String?,
                   val avatarUrl: String?
) {

    companion object {
        fun fromEntity(entity: UserEntity): UserDto {
            require(entity.id != null) { "User id cannot be null!" }
            require(entity.email != null) { "User email cannot be null!" }
            require(entity.firstName != null) { "User first name cannot be null!" }
            require(entity.lastName != null) { "User last name cannot be null!" }

            return UserDto(entity.id, entity.email!!, entity.firstName, entity.lastName, entity.avatarUrl)
        }
    }
}
