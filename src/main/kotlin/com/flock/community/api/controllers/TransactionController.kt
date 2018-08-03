package com.flock.community.api.controllers

import com.flock.community.api.model.User
import com.flock.community.api.repositories.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/transactions")
open class TransactionController(private val userRepository: UserRepository) {

    @GetMapping()
    @PreAuthorize("hasAuthority('TransactionAuthorities.READ')")
    fun findAll(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }

}