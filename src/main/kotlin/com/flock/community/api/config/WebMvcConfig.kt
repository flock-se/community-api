package com.flock.community.api.config

import community.flock.eco.feature.member.MemberConfiguration
import community.flock.eco.feature.payment.PaymentConfiguration
import community.flock.eco.feature.user.UserConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry

@Configuration
class WebMvcConfig : WebMvcConfigurer{
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/ui/**")
                .addResourceLocations("/index.html", "classpath:/index.html")
                .addResourceLocations("/main.js", "classpath:/main.js")
                .addResourceLocations("/donation.html", "classpath:/donation.html")
    }}