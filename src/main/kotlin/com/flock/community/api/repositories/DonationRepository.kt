package com.flock.community.api.repositories

import com.flock.community.api.model.Donation
import com.flock.community.api.model.Member
import com.flock.community.api.model.Transaction
import com.flock.community.api.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

@Service
interface DonationRepository : PagingAndSortingRepository<Donation, Long>{

    @Query("SELECT d FROM Donation d WHERE d.member.id = ?1")
    fun findByMemberId(name: Long): List<Donation>
}


