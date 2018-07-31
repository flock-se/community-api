package com.flock.community.api.controllers

import com.flock.community.api.model.User
import com.flock.community.api.repositories.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolderStrategy
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping("/api/users")
open class UserController(private val userRepository: UserRepository) {

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    fun findMe(principal: Principal): Principal {
        return principal
    }

    @GetMapping("/strategy")
    @PreAuthorize("isAuthenticated()")
    fun findStrategy(): String? {
        return SecurityContextHolder.getContextHolderStrategy().javaClass.name
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
    fun save(@RequestBody user: User, @PathVariable id: Long?): User {
        if (id != null) {
            return userRepository.save(user.copy(
                    id = id))
        }
        return userRepository.save(user)
    }

}