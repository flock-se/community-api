package com.flock.community.api.controllers

import com.flock.community.api.services.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MemberController(private val memberService: MemberService) {

    @GetMapping("/")
    fun findAll() {

    }
}