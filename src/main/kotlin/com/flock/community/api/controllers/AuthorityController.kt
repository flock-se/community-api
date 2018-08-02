package com.flock.community.api.controllers

import com.flock.community.api.authorities.Authority
import com.flock.community.api.authorities.MemberAuthorities
import com.flock.community.api.authorities.TransactionAuthorities
import com.flock.community.api.authorities.UserAuthorities
import com.flock.community.api.service.AuthorityService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
@RequestMapping("/api/authorities")
open class AuthorityController(val authorityService: AuthorityService) {

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    fun findAll(): List<String> {
        return authorityService.findAll()
    }

}