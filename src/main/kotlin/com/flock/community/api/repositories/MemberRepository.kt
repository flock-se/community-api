package com.flock.community.api.repositories

import com.flock.community.api.model.Member
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

import org.springframework.stereotype.Service


@Service
interface MemberRepository : CrudRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.id IN ?1")
    fun findByIds(ids: List<Long>): List<Member>

    @Query("SELECT m FROM Member m WHERE m.firstName LIKE ?1 OR m.surName LIKE ?1")
    fun findByName(name: String): List<Member>

}


