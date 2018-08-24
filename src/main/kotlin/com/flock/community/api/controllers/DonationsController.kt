package com.flock.community.api.controllers

import com.flock.community.api.model.Donation
import com.flock.community.api.model.Frequency
import com.flock.community.api.repositories.DonationRepository
import com.flock.community.api.service.BuckarooService
import community.flock.eco.feature.member.model.Member
import community.flock.eco.feature.payment.model.PaymentTransaction
import community.flock.eco.feature.payment.model.PaymentTransactionStatus
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/donations")
open class DonationsController(
        private val buckarooService: BuckarooService,
        private val donationRepository: DonationRepository) {


    data class Donate(

            val amount: Double,
            val issuer: String,

            val member: Member? = null,

            val newsletter: Boolean,
            val agreeOnTerms: Boolean
    )

    @GetMapping
    fun donate(): List<Donation>? {
        return donationRepository.findAll().toList()
    }

    @PostMapping("/donate")
    fun donate(@RequestBody donate: Donate): String {

        val buckarooTransaction = buckarooService.createTransaction(donate.amount, "Donate", donate.issuer)

        val transaction = PaymentTransaction(
                amount = donate.amount,
                reference = buckarooTransaction.reference,
                status=PaymentTransactionStatus.PENDING
        )

        val donation = Donation(
                date = Date(),
                amount = donate.amount,
                frequency = Frequency.ONCE,
                member = donate.member,
                transactions = setOf(transaction)
        )

        donationRepository.save(donation)

        return buckarooTransaction.redirectUrl
    }

}

