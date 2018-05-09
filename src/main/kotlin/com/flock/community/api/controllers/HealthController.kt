package com.flock.community.api.controllers

import com.flock.community.api.model.Member
import com.flock.community.api.repositories.MemberRepository
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/_ah")
open class HealthController {

    @GetMapping("/health")
    fun health():String {
        return "OK"
    }

}