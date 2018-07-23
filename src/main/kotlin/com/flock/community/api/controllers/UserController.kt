package com.flock.community.api.controllers

import com.flock.community.api.model.User
import com.flock.community.api.repositories.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RepositoryRestController
@RequestMapping("/api/users")
open class UserController(private val userRepository: UserRepository) {

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    fun findMe(principal: Principal): Principal {
        return principal
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('UserAuthorities.READ')")
    fun findAll(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('UserAuthorities.READ')")
    fun findById(@PathVariable id: Long): User {
        return userRepository.findById(id).orElse(null)
    }

    @PostMapping()
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UserAuthorities.WRITE')")
    fun save(@RequestBody user: User, @PathVariable id: Long): User {
        if (id != null) {
            val user = user.copy(id)
            return userRepository.save(user)
        }
        return userRepository.save(user)
    }

}