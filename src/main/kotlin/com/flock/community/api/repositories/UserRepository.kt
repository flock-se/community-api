package com.flock.community.api.repositories

import com.flock.community.api.model.Member
import com.flock.community.api.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.security.core.userdetails.UserDetailsService

import org.springframework.stereotype.Service
import java.io.Serializable


@Service
interface UserRepository : PagingAndSortingRepository<User, Long>, Serializable {

    fun findByReference(name: String): User?
}


