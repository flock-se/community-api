package com.flock.community.api.controllers

import com.flock.community.api.model.Member
import com.flock.community.api.services.MemberService
import org.springframework.web.bind.annotation.*


@RestController()
@RequestMapping("/members")
open class MemberController(private val memberService: MemberService) {

    @GetMapping()
    fun findAll(): List<Member> {
        return memberService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): Member? {
        return memberService.findById(id.toInt())
    }

    @PostMapping("")
    fun findById(): Long? {
        return memberService.create()
    }

}