package com.flock.community.api.controllers

import com.flock.community.api.model.Donation
import com.flock.community.api.model.Frequency
import com.flock.community.api.repositories.DonationRepository
import com.flock.community.api.service.BuckarooService
import community.flock.eco.feature.members.model.Member
import community.flock.eco.feature.payments.model.PaymentTransaction
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/api/donate")
open class DonateController(
        private val buckarooService: BuckarooService,
        private val donationRepository: DonationRepository) {

    data class Donate(

            val amount: Double,
            val issuer: String,

            val member: Member? = null,

            val newsletter: Boolean,
            val agreeOnTerms: Boolean
    )

    @PostMapping()
    fun donate(@RequestBody donate: Donate): String {

        val buckarooTransaction = buckarooService.createTransaction(donate.amount, "Donate", donate.issuer)

        val transaction = PaymentTransaction(
                amount = donate.amount,
                reference = buckarooTransaction.reference
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

