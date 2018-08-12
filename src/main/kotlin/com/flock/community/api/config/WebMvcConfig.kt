package com.flock.community.api.config

import community.flock.eco.feature.members.MemberConfiguration
import community.flock.eco.feature.payments.PaymentConfiguration
import community.flock.eco.feature.users.UserConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry

@Configuration
@EnableJpaRepositories(basePackages = ["com.flock.community.api"])
@ComponentScan(basePackages = ["com.flock.community.api"])
@EntityScan("com.flock.community.api.*")
@Import(UserConfiguration::class,
        MemberConfiguration::class,
        PaymentConfiguration::class)
class WebMvcConfig : WebMvcConfigurer{
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/ui/**")
                .addResourceLocations("/ui/", "classpath:/")
    }}