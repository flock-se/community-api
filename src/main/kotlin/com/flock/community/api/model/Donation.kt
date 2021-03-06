package com.flock.community.api.model

import community.flock.eco.feature.member.model.Member
import community.flock.eco.feature.payment.model.PaymentTransaction
import java.util.*
import javax.persistence.*

@Entity
data class Donation(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        val date: Date,
        val amount: Double,
        val frequency: Frequency,

        @ManyToOne()
        val member: Member?,

        @OneToMany()
        val transactions: Set<PaymentTransaction>

)

enum class Frequency {
    ONCE,
    YEARLY
}

