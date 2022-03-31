package com.sorsix.CarSharing.security

import com.sorsix.CarSharing.service.MyUsersService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(val myUsersService: MyUsersService) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(myUsersService)?.passwordEncoder(getPasswordEncoder())
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers(HttpMethod.OPTIONS, "/**")
    }

    override fun configure(http: HttpSecurity?) {
        http?.cors()?.and()?.csrf()?.disable()
            ?.authorizeRequests()
            ?.antMatchers("/api/reservation/create")?.permitAll() //.hasAnyRole("ROLE_DRIVER")
            ?.antMatchers("/api/vehicle/create")?.permitAll()
            ?.antMatchers("/api/user/create")?.permitAll()
            ?.antMatchers("api/location/create")?.permitAll()
            ?.antMatchers("/authenticate")?.permitAll()
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(10)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}