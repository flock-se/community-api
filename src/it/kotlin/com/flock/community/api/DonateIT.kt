package com.flock.community.api


import com.fasterxml.jackson.databind.ObjectMapper
import com.flock.community.api.controllers.DonateController
import com.flock.community.api.model.Member
import com.flock.community.api.repositories.DonationRepository
import com.flock.community.api.repositories.MemberRepository
import com.flock.community.api.repositories.TransactionRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class DonateIT {

    val mapper = ObjectMapper()

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var donationRepository: DonationRepository

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Test
    fun donationWithMember() {

        val email = UUID.randomUUID().toString();

        val member = Member(
                firstName = "DonateFirstName",
                surName = "DonateSurName",
                email = email
        )

        val donate = DonateController.Donate(
                member = member,
                amount = 10.00,
                issuer = "INGBNL2A",
                agreeOnTerms = true,
                newsletter = true
        )

        mockMvc.perform(post("/api/donate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(donate)))
                .andDo(print())
                .andExpect(status().isOk())

        val memberRes = memberRepository.findByEmail(email).first()
        val donationList = donationRepository.findByMemberId(memberRes.id)
        val donationItem = donationRepository.findById(donationList.first().id)

        assertEquals(email, memberRes.email)
        assertEquals(memberRes, donationList.first().member)
        assertEquals(1, donationItem.get().transactions.size)

    }

}