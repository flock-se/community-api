package com.flock.community.api.controllers

import com.flock.community.api.service.BuckarooService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/buckaroo")
open class BuckarooController(private val buckarooService: BuckarooService) {

    @PostMapping("/create")
    fun create(): String {
        return buckarooService.createTransaction(100.0, "aaaa", "INGBNL2A")
    }

    @PostMapping("/succes")
    fun succes(): String {
        return buckarooService.createTransaction(100.0, "aaaa", "INGBNL2A")
    }


}

