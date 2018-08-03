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
@RequestMapping("/donate")
open class DonateController(private val buckarooService: BuckarooService, private val transactionRepository: TransactionRepository, private val memberRepository: MemberRepository) {

    data class Donate(

            val amount: Double,
            val issuer: String,

            val member: Member,

            val newsletter: Boolean,
            val agreeOnTerms: Boolean
    )

    @PostMapping()
    fun donate(@RequestBody donate: Donate): String {

        val buckarooTransaction = buckarooService.createTransaction(donate.amount, "Donate", donate.issuer)
        val transaction = Transaction(
                amount = donate.amount,
                member = memberRepository.save(donate.member),
                reference = buckarooTransaction.reference
        )

        transactionRepository.save(transaction)

        return buckarooTransaction.redirectUrl
    }

}

