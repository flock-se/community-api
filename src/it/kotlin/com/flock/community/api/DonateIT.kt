package com.flock.community.api


import com.fasterxml.jackson.databind.ObjectMapper
import com.flock.community.api.controllers.DonateController
import com.flock.community.api.repositories.DonationRepository
import community.flock.eco.feature.members.model.Member
import community.flock.eco.feature.members.repositories.MemberRepository
import community.flock.eco.feature.payments.repositories.PaymentTransactionRepository
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
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
@AutoConfigureTestDatabase
class DonateIT {

    val mapper = ObjectMapper()

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var donationRepository: DonationRepository

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

        assertEquals(email, memberRes.email)
        assertEquals(10.0, donationList.first().amount, 0.0)
        assertNull( donationList.first().transactions.toList()[0].confirmed)

        // Confirm Transcation
        mockMvc.perform(post("/api/buckaroo/success")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"Transaction\": {\"Key\": \"${donationList.first().transactions.first().reference}\"}}"))
                .andDo(print())
                .andExpect(status().isOk())


        val donationListConfirmed = donationRepository.findByMemberId(memberRes.id)
        assertEquals(email, memberRes.email)
        assertNotNull(donationListConfirmed.first().transactions.toList()[0].confirmed)

    }

    @Test
    fun donationAnoniem() {

        val donate = DonateController.Donate(
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



    }

}