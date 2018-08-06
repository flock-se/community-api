package com.flock.community.api.controllers

import com.flock.community.api.model.Transaction
import com.flock.community.api.repositories.TransactionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/transactions")
open class TransactionController(private val transactionRepository: TransactionRepository) {

    @GetMapping()
    @PreAuthorize("hasAuthority('TransactionAuthorities.READ')")
    fun findAll(pageable: Pageable): Page<Transaction> {
        return transactionRepository.findAll(pageable)
    }

}