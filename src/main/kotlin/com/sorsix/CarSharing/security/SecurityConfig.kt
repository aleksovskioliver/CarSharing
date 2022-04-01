package com.sorsix.CarSharing.security

import com.sorsix.CarSharing.filters.JwtRequestFilter
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
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(val myUsersService: MyUsersService, val jwtRequestFilter: JwtRequestFilter) :
    WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(myUsersService)?.passwordEncoder(getPasswordEncoder())
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers(HttpMethod.OPTIONS, "/**")
    }

    override fun configure(http: HttpSecurity?) {
        http?.cors()?.and()?.csrf()?.disable()
            ?.authorizeRequests()
            ?.antMatchers("/api/reservation/create")?.hasAnyRole("DRIVER")
            ?.antMatchers("/api/vehicle/create")?.hasAnyRole("DRIVER")
            ?.antMatchers("/api/reservation/addCustomer")?.hasAnyRole("DRIVER","CUSTOMER")
            ?.antMatchers("/api/user/create")?.permitAll()
            ?.antMatchers("/api/location/create")?.permitAll()
            ?.antMatchers("/authenticate")?.permitAll()
            ?.and()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http?.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
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