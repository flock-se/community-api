package com.flock.community.api.config

import community.flock.eco.feature.member.authorities.MemberAuthority
import community.flock.eco.feature.payment.authorities.PaymentAuthority
import community.flock.eco.feature.user.authorities.UserAuthority
import community.flock.eco.feature.user.model.User
import community.flock.eco.feature.user.repositories.UserRepository
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.SimpleGrantedAuthority


@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
open class SecurityConfig() : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http
                .authorizeRequests()
                .antMatchers("/login**", "/webjars/**", "/error**", "/resources/**").permitAll()
                .antMatchers("/_ah/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/donations/donate").permitAll()
                .antMatchers(HttpMethod.POST, "/api/payment/buckaroo/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()

    }

    @Bean
    fun principalExtractor(userRepository: UserRepository): PrincipalExtractor {
        return PrincipalExtractor {
            val reference = it.get("email").toString()

            val user = userRepository.findByReference(reference)

            if (user == null) {
                userRepository.save(User(
                        reference = reference,
                        name = it.get("name").toString(),
                        email = it.get("email").toString(),
                        authorities = setOf(
                                UserAuthority.READ.toName(),
                                UserAuthority.WRITE.toName(),
                                PaymentAuthority.READ.toName(),
                                PaymentAuthority.WRITE.toName(),
                                MemberAuthority.READ.toName(),
                                MemberAuthority.WRITE.toName()
                        )
                ))
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