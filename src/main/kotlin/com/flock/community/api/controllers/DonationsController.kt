package com.flock.community.api.controllers

import com.flock.community.api.model.Donation
import com.flock.community.api.repositories.DonationRepository
import com.flock.community.api.service.BuckarooService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/donations")
open class DonationsController(
        private val buckarooService: BuckarooService,
        private val donationRepository: DonationRepository) {


    @GetMapping
    fun donate(): List<Donation>? {
        return donationRepository.findAll().toList()
    }

}

