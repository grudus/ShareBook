package com.pwr.sharebook.spring.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import com.pwr.sharebook.user.auth.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Value

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig

@Autowired
constructor(private val passwordEncoder: PasswordEncoder,
            private val userDetailsService: UserDetailsServiceImpl,
            private val jwtUtils: JwtUtils,
            private val jwtProvider: JwtProvider,
            @Value("\${sharebook.frontend.origin}")
            private val frontendOrigin: String
            ) : WebSecurityConfigurerAdapter() {


    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/h2/**").permitAll() // todo remove when deploys
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .addFilterBefore(CorsFilter(frontendOrigin), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(JwtLoginFilter("/auth/login", userDetailsService, passwordEncoder, jwtUtils), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(JwtAuthTokenFilter(jwtProvider, userDetailsService, jwtUtils), UsernamePasswordAuthenticationFilter::class.java)
    }
}
