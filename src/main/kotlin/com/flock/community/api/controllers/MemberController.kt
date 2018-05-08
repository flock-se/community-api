package com.flock.community.api.controllers

import com.flock.community.api.model.Member
import com.flock.community.api.repositories.MemberRepository
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/members")
open class MemberController(private val memberRepository: MemberRepository) {

    @GetMapping
    fun findAll(): List<Member> {
        return memberRepository.findAll().toList()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): Optional<Member> {
        return memberRepository.findById(id.toInt())
    }

    @PostMapping(consumes = ["application/json"])
    fun create(@RequestBody member: Member): Member {
        return memberRepository.save(member)
    }

    @PostMapping("test")
    fun test(): String {
        return "Hallo213"
    }

}