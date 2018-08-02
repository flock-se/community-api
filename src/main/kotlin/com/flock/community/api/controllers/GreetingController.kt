package com.flock.community.api.controllers

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            "Hello, $name, from a SpringBoot Application written in Kotlin, running on Google App Engine Java8 Standard..."

    @GetMapping("/itsme")
    @PreAuthorize("hasAuthority('XXX')")
    fun itsme(principal: Principal) = principal

}
