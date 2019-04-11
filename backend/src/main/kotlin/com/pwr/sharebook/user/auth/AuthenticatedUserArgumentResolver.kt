package com.pwr.sharebook.user.auth

import com.pwr.sharebook.user.AuthenticatedUser
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


class AuthenticatedUserArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(methodParameter: MethodParameter): Boolean {
        return methodParameter.parameterType.isAssignableFrom(AuthenticatedUser::class.java)
    }

    override fun resolveArgument(methodParameter: MethodParameter, modelAndViewContainer: ModelAndViewContainer?, nativeWebRequest: NativeWebRequest, webDataBinderFactory: WebDataBinderFactory?): Any? {
        val authentication = SecurityContextHolder.getContext().authentication
        val userEntity = (authentication.principal as UserDetailsImpl).user
        return AuthenticatedUser(userEntity.id!!, userEntity.email!!)
    }
}
