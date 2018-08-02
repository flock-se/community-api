package com.flock.community.api.repositories

import com.flock.community.api.model.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service


@Service
interface UserRepository : PagingAndSortingRepository<User, Long> {

    fun findByReference(name: String): User?
}

