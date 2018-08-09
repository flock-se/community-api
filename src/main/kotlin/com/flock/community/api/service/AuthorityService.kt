package com.flock.community.api.service

import community.flock.eco.core.authorities.Authority
import community.flock.eco.feature.members.authorities.MemberAuthority
import community.flock.eco.feature.payments.authorities.PaymentAuthority
import community.flock.eco.feature.users.authorities.UserAuthority
import org.springframework.stereotype.Service

@Service
open class AuthorityService {
    fun findAll(): List<String> {
        val list = arrayOf(
                MemberAuthority.values(),
                PaymentAuthority.values(),
                UserAuthority.values()
        )

        return list
                .flatten()
                .map {
                    (it as Authority).toName()
                }
    }
}