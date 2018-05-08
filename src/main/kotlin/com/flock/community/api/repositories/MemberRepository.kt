package com.flock.community.api.repositories

import com.flock.community.api.model.Member
import org.springframework.data.repository.CrudRepository

import org.springframework.stereotype.Service


@Service
interface MemberRepository: CrudRepository<Member, Int> {

    fun findByName(name: String): List<Member>
}


