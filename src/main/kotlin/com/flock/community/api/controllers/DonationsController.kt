package com.flock.community.api.controllers

import com.flock.community.api.model.Donation
import com.flock.community.api.model.Frequency
import com.flock.community.api.repositories.DonationRepository
import com.flock.community.api.service.PaymentBuckarooService
import community.flock.eco.feature.member.model.Member
import community.flock.eco.feature.payment.model.PaymentTransaction
import community.flock.eco.feature.payment.model.PaymentTransactionStatus
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/donations")
open class DonationsController(
        private val buckarooService: PaymentBuckarooService,
        private val donationRepository: DonationRepository) {

    data class Payment(
            val paymentMethod: PaymentBuckarooService.PaymentMethod,
            val issuer: String,
            val amount: Double
    )

    data class Donate(

            val payment: Payment,

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

        val buckarooTransaction = buckarooService.createTransaction(donate.payment.paymentMethod, donate.payment.issuer, donate.payment.amount, "Donate")

        val transaction = PaymentTransaction(
                amount = donate.payment.amount,
                reference = buckarooTransaction.reference,
                status=PaymentTransactionStatus.PENDING
        )

        val donation = Donation(
                date = Date(),
                amount = donate.payment.amount,
                frequency = Frequency.ONCE,
                member = donate.member,
                transactions = setOf(transaction)
        )

        donationRepository.save(donation)

        return buckarooTransaction.redirectUrl
    }

}

