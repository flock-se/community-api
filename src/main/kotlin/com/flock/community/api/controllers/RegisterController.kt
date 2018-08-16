package com.flock.community.api.controllers

import community.flock.eco.feature.member.model.Member
import community.flock.eco.feature.member.model.MemberStatus
import community.flock.eco.feature.member.repositories.MemberRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/register")
open class RegisterController(private val memberRepository: MemberRepository) {

    @PostMapping()
    fun create(@RequestBody member: Member): Member {
        return memberRepository.save(
                member.copy(status = MemberStatus.NEW)
        )
    }

}