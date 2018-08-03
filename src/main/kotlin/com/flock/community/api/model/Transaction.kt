package com.flock.community.api.model

import javax.persistence.*

@Entity
data class Transaction(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @ManyToOne
        val member: Member,

        val amount: Double,
        val reference: String,
        val confirmed: Boolean = false

)