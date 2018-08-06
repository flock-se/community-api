package com.flock.community.api.model

import java.util.*
import javax.persistence.*

@Entity
data class Member(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        val firstName: String,
        val infix: String? = null,
        val surName: String,

        val email: String,

        val phoneNumber: String? = null,

        val street: String? = null,
        val houseNumber: String? = null,
        val houseNumberExtension: String? = null,
        val postalCode: String? = null,
        val city: String? = null,

        val gender: Gender? = null,
        val birthDate: Date? = null,

        @ManyToMany(cascade = [(CascadeType.ALL)])
        val groups: Set<MemberGroup> = setOf(),

        val status: Status = Status.NEW

)

@Entity
data class MemberGroup(

        @Id
        val code: String,
        val name: String
)

enum class Status {
    NEW,
    ACTIVE,
    DISABLED
}

enum class Gender {
    MALE,
    FEMALE,
    OTHER
}

