package com.flock.community.api.repositories

import com.flock.community.api.Application
import com.flock.community.api.config.WebMvcConfig
import com.flock.community.api.controllers.DonateController
import com.flock.community.api.model.*
import community.flock.eco.feature.members.MemberConfiguration
import community.flock.eco.feature.members.model.Member
import community.flock.eco.feature.members.model.MemberGroup
import community.flock.eco.feature.members.repositories.MemberRepository
import community.flock.eco.feature.users.UserConfiguration
import junit.framework.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import javax.annotation.PostConstruct

@DataJpaTest
@RunWith(SpringRunner::class)
@AutoConfigureTestDatabase
@Import(WebMvcConfig::class)
@Ignore
open class DonationRepositoryTest {


    @Autowired
    lateinit var donationRepository: DonationRepository

    @Test
    fun testsCreate() {
        val email = UUID.randomUUID().toString();

        val group = MemberGroup(
                code = "TEST",
                name = "Test"
        )

        val member = Member(
                firstName = "DonateFirstName",
                surName = "DonateSurName",
                email = email,
                groups = setOf(group)
        )

        val transaction = Transaction(
                amount = 10.10,
                reference = "123123"
        )

        val donation = Donation(
                date = Date(),
                amount = 10.00,
                member = member,
                transactions = setOf(transaction),
                frequency = Frequency.ONCE
        )

        donationRepository.save(donation)

        val res = donationRepository.findByMemberId(donation.member!!.id).first()

        assertEquals("TEST", res.member!!.groups.toList()[0].code)
    }

}