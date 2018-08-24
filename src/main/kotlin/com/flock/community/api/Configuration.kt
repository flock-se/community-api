package com.flock.community.api

import com.flock.community.api.config.WebMvcConfig
import com.flock.community.api.config.WebSecurityConfig
import community.flock.eco.feature.member.controllers.MemberController
import community.flock.eco.feature.member.controllers.MemberGroupController
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableJpaRepositories
@EntityScan
@Import(
        WebMvcConfig::class,
        WebSecurityConfig::class)
class Configuration : WebMvcConfigurer