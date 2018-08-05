package com.flock.community.api.repositories

import com.flock.community.api.model.Transaction
import com.flock.community.api.model.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service


@Service
interface TransactionRepository : PagingAndSortingRepository<Transaction, Long>{
    fun findByReference(name: String): Transaction?
}


