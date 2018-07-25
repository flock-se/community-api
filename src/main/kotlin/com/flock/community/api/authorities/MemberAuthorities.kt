package com.flock.community.api.authorities

import org.springframework.stereotype.Component

enum class MemberAuthorities: Authority{
    READ,
    WRITE
}