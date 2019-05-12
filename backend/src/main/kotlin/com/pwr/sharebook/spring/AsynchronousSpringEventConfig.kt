package com.pwr.sharebook.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.context.event.ApplicationEventMulticaster


@Configuration
class AsynchronousSpringEventsConfig {

    @Bean(name = ["applicationEventMulticaster"])
    fun simpleApplicationEventMulticaster(): ApplicationEventMulticaster {
        val eventMulticaster = SimpleApplicationEventMulticaster()

        eventMulticaster.setTaskExecutor(SimpleAsyncTaskExecutor())
        return eventMulticaster
    }
}
