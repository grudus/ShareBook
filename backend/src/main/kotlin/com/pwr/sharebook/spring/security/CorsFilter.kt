package com.pwr.sharebook.spring.security

import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class CorsFilter
constructor(private val allowedOrigin: String): GenericFilterBean() {


    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val response = res as HttpServletResponse
        val request = req as HttpServletRequest

        response.setHeader("Access-Control-Allow-Origin", allowedOrigin)
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS")
        response.setHeader("Access-Control-Allow-Headers", "withCredentials, content-type")
        response.setHeader("Access-Control-Allow-Credentials", "true")

        if (request.method != "OPTIONS")
            chain.doFilter(request, response)
    }
}
