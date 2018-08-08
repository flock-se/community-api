package com.flock.community.api.authorities

import community.flock.eco.core.authorities.Authority

enum class TransactionAuthority: Authority {
    READ,
    WRITE
}