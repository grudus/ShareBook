package com.pwr.sharebook.spring

import com.pwr.sharebook.group.GroupRepository
import com.pwr.sharebook.group.usergroup.UserGroupEntity
import com.pwr.sharebook.group.usergroup.UserGroupRepository
import com.pwr.sharebook.spring.security.JwtProvider
import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.user.UserRepository
import com.pwr.sharebook.user.auth.AuthenticatedUserArgumentResolver
import com.pwr.sharebook.user.role.RoleEntity
import com.pwr.sharebook.user.role.RoleRepository
import com.pwr.sharebook.user.role.RoleType
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class ShareBookContext : WebMvcConfigurer {

    @Bean
    fun passwordEncoder(): PasswordEncoder =
            BCryptPasswordEncoder(12)


    @Bean
    fun jwtProvider(@Value("\${jwt.secret}") secret: String, @Value("\${jwt.expiration}") expiration: Int): JwtProvider {
        return JwtProvider(secret, expiration)
    }


    @Bean
    fun commandLineRunner(userRepository: UserRepository,
                          passwordEncoder: PasswordEncoder,
                          roleRepository: RoleRepository,
                          groupRepository: GroupRepository
                          ): CommandLineRunner {
        return CommandLineRunner {

            if (roleRepository.findAll().isEmpty()) {
                roleRepository.save(RoleEntity(null, RoleType.STUDENT))
            }

            if (userRepository.findAll().isEmpty()) {
                val student = roleRepository.findByName(RoleType.STUDENT)!!
                userRepository.save(mockAdminUser(passwordEncoder.encode("admin@admin.com"), student))
            }

            println("_____ Application has started ______")
        }
    }

    private fun mockAdminUser(password: String, role: RoleEntity): UserEntity =
            UserEntity(null, "admin@admin.com", password, "https://pbs.twimg.com/profile_images/898948764152541184/z6y2UNbw_400x400.jpg", "Admin", "Adminowy", role)


    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(AuthenticatedUserArgumentResolver())
    }

}
