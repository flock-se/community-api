package com.flock.community.api.config

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import java.io.File
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext



val objectMapper = ObjectMapper();

@Configuration
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
    }

    @Bean
    open fun clientRegistrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(this.googleClientRegistration())
    }

    @Bean
    open fun authorizedClientService(): OAuth2AuthorizedClientService {
        return InMemoryOAuth2AuthorizedClientService(this.clientRegistrationRepository())
    }

    private fun googleClientRegistration(): ClientRegistration {

        val resource = applicationContext.getResource("classpath:client_secret.json")
        val json = objectMapper.readTree(resource.file)

        val clientId = json.get("installed").get("client_id")
        val clientSecret = json.get("installed").get("client_secret")

        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
            .clientId(clientId.asText())
            .clientSecret(clientSecret.asText())
            .build()
    }

}