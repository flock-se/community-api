package com.flock.community.api.controllers

import com.fasterxml.jackson.databind.node.ObjectNode
import com.flock.community.api.repositories.TransactionRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/api/buckaroo")
open class BuckarooController(
        private val transactionRepository: TransactionRepository) {

    @PostMapping("/success")
    fun succes(obj: ObjectNode): String {
        val key = obj.get("Transaction").get("Key").asText()

        transactionRepository.findByReference(key)?.let {
            transactionRepository.save(it.copy(
                    confirmed = Date()
            ))
        }

        return "OK"
    }

    @PostMapping("/error")
    fun error(): String {
        return "OK"
    }


}

