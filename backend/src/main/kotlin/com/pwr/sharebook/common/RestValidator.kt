package com.pwr.sharebook.common

import org.springframework.validation.Errors
import org.springframework.validation.Validator

abstract class RestValidator<T: Any> : Validator {

    abstract fun validate(request: T): List<String>

    @Suppress("UNCHECKED_CAST") // `supports` method takes care about types
    override fun validate(target: Any, errors: Errors) {
        val errorKeys = validate(target as T)
        errorKeys.forEach { error ->
            errors.reject(error)
        }
    }

    // we assume that nobody uses it outside of @Valid context in request method parameter
    override fun supports(clazz: Class<*>): Boolean = true
}
