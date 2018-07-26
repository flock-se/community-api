package com.flock.community.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.flock.community.api.model.User
import com.flock.community.api.repositories.UserRepository
import com.flock.community.api.authorities.UserAuthorities
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder


private val TOKEN_HEADER = "Authorization"

val objectMapper = ObjectMapper()

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
open class SecurityConfig() : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http
                .csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/", "/login**", "/webjars/**", "/error**").permitAll()
                    .antMatchers("/_ah/**").permitAll()
                    .mvcMatchers("/api/register").permitAll()
                    .antMatchers("/api/buckaroo/**").permitAll()
                .anyRequest()
                    .authenticated()
    }

    @Bean
    fun principalExtractor(userRepository: UserRepository): PrincipalExtractor {
        return PrincipalExtractor {
            val reference = it.get("email").toString()

            val user = userRepository.findByReference(reference)

            if (user == null) {
                val user = User(
                        reference = reference,
                        name = it.get("name").toString(),
                        email = it.get("email").toString()
                )
                userRepository.save(user)
            } else {
                user
            }
        }
    }

    @Bean
    fun authoritiesExtractor(userRepository: UserRepository): AuthoritiesExtractor {

        return AuthoritiesExtractor {
            val reference = it.get("email").toString()
            val user = userRepository.findByReference(reference)
            user?.authorities?.map { SimpleGrantedAuthority(it) } ?: listOf()
        }
    }

}