package com.flock.community.api.repositories

import com.flock.community.api.model.Member
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

import org.springframework.stereotype.Service


@Service
interface MemberRepository : CrudRepository<Member, Int> {

    @Query("SELECT m FROM Member m WHERE m.id IN ?1")
    fun findByIds(ids: List<Int>): List<Member>

    fun findByName(name: String): List<Member>
}


