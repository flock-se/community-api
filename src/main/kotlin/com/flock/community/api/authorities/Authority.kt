package com.flock.community.api.authorities

import java.io.Serializable

interface Authority : Serializable {

    fun toName():String {
        return javaClass.simpleName + "." + toString()
    }
}