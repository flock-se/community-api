package com.flock.community.api.config

import com.flock.community.api.authorities.MemberAuthorities
import com.flock.community.api.authorities.TransactionAuthorities
import com.flock.community.api.authorities.UserAuthorities
import com.flock.community.api.model.User
import com.flock.community.api.repositories.UserRepository
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
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
open class SecurityConfig() : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http
                .authorizeRequests()
                .antMatchers("/login**", "/webjars/**", "/error**", "/resources/**").permitAll()
                .antMatchers("/_ah/**").permitAll()
                .antMatchers( HttpMethod.POST,"/api/donate", "/api/register").permitAll()
                .antMatchers( HttpMethod.POST,"/api/donate", "/api/register").permitAll()
                .antMatchers(HttpMethod.POST,"/api/buckaroo/**").permitAll()
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
                                UserAuthorities.READ.toName(),
                                UserAuthorities.WRITE.toName(),
                                TransactionAuthorities.READ.toName(),
                                TransactionAuthorities.WRITE.toName(),
                                MemberAuthorities.READ.toName(),
                                MemberAuthorities.WRITE.toName()
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