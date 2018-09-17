package com.flock.community.api

import com.flock.community.api.config.WebMvcConfig
import com.flock.community.api.config.WebSecurityConfig
import community.flock.eco.feature.member.MemberConfiguration
import community.flock.eco.feature.member.controllers.MemberController
import community.flock.eco.feature.member.controllers.MemberGroupController
import community.flock.eco.feature.payment.PaymentConfiguration
import community.flock.eco.feature.user.UserConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableJpaRepositories
@EntityScan
@ComponentScan
@Import(UserConfiguration::class,
        MemberConfiguration::class,
        PaymentConfiguration::class)
class Configuration : WebMvcConfigurer