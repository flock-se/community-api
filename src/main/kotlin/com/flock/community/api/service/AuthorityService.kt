package com.flock.community.api.service

import com.flock.community.api.authorities.Authority
import com.flock.community.api.authorities.MemberAuthorities
import com.flock.community.api.authorities.TransactionAuthorities
import com.flock.community.api.authorities.UserAuthorities
import org.springframework.stereotype.Service

@Service
open class AuthorityService {
    fun findAll(): List<String> {
        val list = arrayOf(
                MemberAuthorities.values(),
                TransactionAuthorities.values(),
                UserAuthorities.values()
        )

        return list
                .flatten()
                .map {
                    (it as Authority).toName()
                }
    }
}