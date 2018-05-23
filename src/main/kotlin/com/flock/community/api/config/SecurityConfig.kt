package com.flock.community.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

val objectMapper = ObjectMapper()

@Configuration
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/_ah/**").permitAll()
            .mvcMatchers("/api/register").permitAll()
            .antMatchers("/api/buckaroo/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
    }

    /* To allow Pre-flight [OPTIONS] request from browser */
    @Throws(Exception::class)
    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
    }

}