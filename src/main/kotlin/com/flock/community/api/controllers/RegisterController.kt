package com.flock.community.api.controllers

import com.flock.community.api.model.Member
import com.flock.community.api.model.Status
import com.flock.community.api.repositories.MemberRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/register")
open class RegisterController(private val memberRepository: MemberRepository) {

    @PostMapping()
    fun create(@RequestBody member: Member): Member {
        return memberRepository.save(member.copy(status = Status.NEW))
    }

}