package com.flock.community.api.model

import java.util.*
import javax.persistence.*

@Entity
data class Transaction(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        val amount: Double,

        val reference: String,
        val confirmed: Date? = null

)