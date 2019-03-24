package com.pwr.sharebook.user.registration

data class CreateUserRequest(val email: String,
                             val password: String,
                             val confirmPassword: String,
                             val firstName: String,
                             val lastName: String)
