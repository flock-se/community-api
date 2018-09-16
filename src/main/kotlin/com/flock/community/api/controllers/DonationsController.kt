package com.flock.community.api.controllers

import com.flock.community.api.model.Donation
import com.flock.community.api.model.Frequency
import com.flock.community.api.repositories.DonationRepository
import community.flock.eco.feature.member.model.Member
import community.flock.eco.feature.member.model.MemberField
import community.flock.eco.feature.member.model.MemberFieldType
import community.flock.eco.feature.member.model.MemberGroup
import community.flock.eco.feature.member.repositories.MemberFieldRepository
import community.flock.eco.feature.member.repositories.MemberGroupRepository
import community.flock.eco.feature.member.repositories.MemberRepository
import community.flock.eco.feature.payment.model.PaymentTransaction
import community.flock.eco.feature.payment.model.PaymentTransactionStatus
import community.flock.eco.feature.payment.repositories.PaymentTransactionRepository
import community.flock.eco.feature.payment.services.PaymentBuckarooService
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/donations")
open class DonationsController(
        private val buckarooService: PaymentBuckarooService,
        private val menberRepository: MemberRepository,
        private val memberGroupRepository: MemberGroupRepository,
        private val menberFieldRepository: MemberFieldRepository,
        private val paymentTransactionRepository: PaymentTransactionRepository,
        private val donationRepository: DonationRepository) {

    companion object {
        const val GROUP_DONATION = "DONATION"
        const val FIELD_NEWSLETTER = "newsletter"
        const val FIELD_AGREED_ON_TERMS = "agreed_on_terms"
    }

    val groupDonation = memberGroupRepository
            .findByCode(GROUP_DONATION)
            .orElseGet {
                memberGroupRepository.save(MemberGroup(
                        name = "Donation",
                        code = GROUP_DONATION
                ))
            }

    init {

        if (!isFieldPresent(FIELD_NEWSLETTER)) {
            menberFieldRepository.save(MemberField(
                    name = FIELD_NEWSLETTER,
                    label = "Subscribed for newsletter",
                    type = MemberFieldType.CHECKBOX,
                    disabled = true
            ))
        }

        if (!isFieldPresent(FIELD_AGREED_ON_TERMS)) {
            menberFieldRepository.save(MemberField(
                    name = FIELD_AGREED_ON_TERMS,
                    label = "Agreed on terms",
                    type = MemberFieldType.CHECKBOX,
                    disabled = true
            ))
        }
    }

    private fun isGroupPresent(code: String) = memberGroupRepository.findByCode(code).isPresent
    private fun isFieldPresent(field: String) = menberFieldRepository.findByName(field).isPresent


    data class Payment(
            val paymentMethod: PaymentBuckarooService.PaymentMethod,
            val issuer: String,
            val amount: Double
    )

    data class Donate(

            val payment: Payment,

            val member: Member? = null,

            val newsletter: Boolean,
            val agreedOnTerms: Boolean
    )

    @GetMapping
    fun findAll(): List<Donation>? {
        return donationRepository.findAll().toList()
    }

    @PostMapping("/donate")
    fun donate(@RequestBody donate: Donate): String {

        val member = donate.member?.let {
            menberRepository.save(it.copy(
                    groups = setOf(groupDonation),
                    fields = mapOf(
                            FIELD_NEWSLETTER to donate.newsletter.toString().toLowerCase(),
                            FIELD_AGREED_ON_TERMS to donate.agreedOnTerms.toString().toLowerCase()
                    )
            ))
        }

        val buckarooTransaction = buckarooService.createTransaction(donate.payment.paymentMethod, donate.payment.issuer, donate.payment.amount, "Donate")

        val transaction = paymentTransactionRepository.save(PaymentTransaction(
                amount = donate.payment.amount,
                reference = buckarooTransaction.reference,
                status = PaymentTransactionStatus.PENDING
        ))

        val donation = Donation(
                date = Date(),
                amount = donate.payment.amount,
                frequency = Frequency.ONCE,
                member = member,
                transactions = setOf(transaction)
        )

        donationRepository.save(donation)

        return buckarooTransaction.redirectUrl
    }

}

