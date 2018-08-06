package com.flock.community.api.repositories

import com.flock.community.api.model.Donation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

@Service
interface DonationRepository : PagingAndSortingRepository<Donation, Long> {

    @Query("SELECT d FROM Donation d " +
            "INNER JOIN FETCH d.member m " +
            "INNER JOIN FETCH m.groups g " +
            "INNER JOIN FETCH d.transactions t " +
            "WHERE d.member.id = ?1")
    fun findByMemberId(id: Long): List<Donation>
}


