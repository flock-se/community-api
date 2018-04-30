package com.flock.community.api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController()
@RequestMapping("/users")
open class UserController() {

    @GetMapping("/me")
    fun findAll(principal: Principal): String {
        return principal.name
    }


}