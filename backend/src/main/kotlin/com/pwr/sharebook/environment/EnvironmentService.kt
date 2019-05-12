package com.pwr.sharebook.environment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:application.properties")
class EnvironmentService
@Autowired
constructor(private val env: Environment)
{

    fun getString(key: String): String? =
        env.getProperty(key)


}
