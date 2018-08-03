package com.flock.community.api.controllers

import com.flock.community.api.model.Member
import com.flock.community.api.model.Transaction
import com.flock.community.api.repositories.MemberRepository
import com.flock.community.api.repositories.TransactionRepository
import com.flock.community.api.service.BuckarooService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/buckaroo")
open class BuckarooController(private val buckarooService: BuckarooService, private val transactionRepository: TransactionRepository, private val memberRepository: MemberRepository) {

    @PostMapping("/success")
    fun succes(): String {
        return "OK"
    }

    @PostMapping("/error")
    fun error(): String {
        return "OK"
    }


}

