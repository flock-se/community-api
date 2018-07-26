package com.flock.community.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.security.core.context.SecurityContextHolder

@SpringBootApplication
class Application : SpringBootServletInitializer() {

}

fun main(args: Array<String>) {
    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
    SpringApplication.run(Application::class.java, *args)
}
