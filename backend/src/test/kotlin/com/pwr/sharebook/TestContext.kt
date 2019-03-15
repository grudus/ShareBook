package com.pwr.sharebook



import com.pwr.sharebook.spring.ShareBookContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.*
import javax.sql.DataSource

@Configuration
@Import(ShareBookContext::class)
@PropertySource("classpath:/test.properties")
@ComponentScan("com.pwr.sharebook")
class TestContext {

    @Bean
    @Primary
    fun primaryDataSource(@Value("\${spring.datasource.driver-class-name}") driver: String,
                          @Value("\${spring.datasource.url}") url: String,
                          @Value("\${spring.datasource.username}") username: String,
                          @Value("\${spring.datasource.password}") password: String): DataSource =

            DataSourceBuilder.create()
                    .username(username)
                    .password(password)
                    .url(url)
                    .driverClassName(driver)
                    .build()
}
