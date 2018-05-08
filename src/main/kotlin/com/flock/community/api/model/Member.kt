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
    val status: Status
)

enum class Status{
    ACTIVE
}