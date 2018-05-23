package com.flock.community.api.controllers

import com.fasterxml.jackson.databind.node.ObjectNode
import com.flock.community.api.model.Member
import com.flock.community.api.service.BuckarooService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/buckaroo")
open class BuckarooController(private val buckarooService: BuckarooService) {

    @PostMapping("/donate")
    fun donate(@RequestBody obj: ObjectNode): String {
        val amount = obj.get("amount").asDouble()
        val description = obj.get("description").asText()
        val issuer = obj.get("issuer").asText()
        return buckarooService.createTransaction(amount, description, issuer);
    }

    @PostMapping("/success")
    fun succes(): String {
        return "OK"
    }

    @PostMapping("/error")
    fun error(): String {
        return "OK"
    }


}

