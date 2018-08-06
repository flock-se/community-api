package com.flock.community.api.model

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

        @ManyToOne(cascade = [(CascadeType.ALL)])
        val member: Member?,

        @OneToMany(cascade = [(CascadeType.ALL)])
        val transactions: List<Transaction>

)

enum class Frequency {
    ONCE,
    YEARLY
}
