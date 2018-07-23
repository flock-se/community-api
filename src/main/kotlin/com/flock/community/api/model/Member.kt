package com.flock.community.api.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Member(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    val name: String,
    val email: String,

    val phoneNumber: String? = null,

    val street: String? = null,
    val houseNumber: String? = null,
    val postalCode: String? = null,
    val place: String? = null,

    val gender: Gender? = null,
    val birthDate: Date? = null,

    val status: Status = Status.NEW

)

enum class Status {
    NEW,
    ACTIVE,
    DISABLED
}

enum class Gender {
    MALE,
    FEMALE,
    UNKNOWN
}