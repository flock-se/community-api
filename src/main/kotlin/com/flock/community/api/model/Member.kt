package com.flock.community.api.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Member(

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Int = 0,

    val name: String,
    val email: String,
    val status: Status,

    val street: String? = null,
    val houseNumber: String? = null

)

enum class Status{
    NEW,
    ACTIVE,
    DISABLED
}