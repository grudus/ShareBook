package com.pwr.sharebook.spring

import com.pwr.sharebook.spring.security.JwtProvider
import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.user.UserRepository
import com.pwr.sharebook.user.role.RoleEntity
import com.pwr.sharebook.user.role.RoleRepository
import com.pwr.sharebook.user.role.RoleType
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.Arrays.asList


@Configuration
class ShareBookContext : WebMvcConfigurer {

    @Bean
    fun passwordEncoder(): PasswordEncoder =
            BCryptPasswordEncoder(12)

    @Bean
    fun corsConfigurationSource(@Value("\${songbook.frontend.origin}") origin: String): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf(origin)
        configuration.allowedMethods = asList("GET", "POST", "PUT", "DELETE")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun jwtProvider(@Value("\${jwt.secret}") secret: String, @Value("\${jwt.expiration}") expiration: Int): JwtProvider {
        return JwtProvider(secret, expiration)
    }


    @Bean
    fun commandLineRunner(userRepository: UserRepository, roleRepository: RoleRepository): CommandLineRunner = CommandLineRunner {
        println("no siemanko")

        if (userRepository.findAll().isEmpty()) {
            val findByName: RoleEntity? = roleRepository.findByName(RoleType.STUDENT)
            userRepository.save(UserEntity(null, "mama@tata.com", "abc",  null, "Jakub", "Gruda", findByName))
        }

        println("Users: ${userRepository.findAll()}")
    }

}
