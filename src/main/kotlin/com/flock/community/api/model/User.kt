package com.flock.community.api.model

import javax.persistence.*

@Entity
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @Column(unique = true)
        val reference: String,

        val name: String,
        val email: String,

        @ElementCollection(fetch = FetchType.EAGER)
        val authorities: List<String> = listOf()

)