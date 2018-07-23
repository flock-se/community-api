package com.flock.community.api.authorities

interface Authority {

    fun toName():String {
        return javaClass.simpleName + "." + toString()
    }
}