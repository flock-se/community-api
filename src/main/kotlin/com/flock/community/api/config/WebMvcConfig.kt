package com.flock.community.api.config

import community.flock.eco.feature.members.MemberConfiguration
import community.flock.eco.feature.users.UserConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableJpaRepositories(basePackages = ["com.flock.community.api"])
@ComponentScan(basePackages = ["com.flock.community.api"])
@EntityScan("com.flock.community.api.*")
@Import(UserConfiguration::class,
        MemberConfiguration::class)
class WebMvcConfig : WebMvcConfigurer