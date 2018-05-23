package com.flock.community.api.repositories

import com.flock.community.api.model.Member
import com.flock.community.api.model.Status
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import javax.annotation.PostConstruct

@DataJpaTest
@RunWith(SpringRunner::class)
open class MemberRepositoryTest {


    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var memberRepository: MemberRepository

    @PostConstruct
    fun init() {
        memberRepository.save(Member(
            name = "member1",
            email = "member1@gmail.com",
            status = Status.ACTIVE
        ))
        memberRepository.save(Member(
            name = "member2",
            email = "member2@gmail.com",
            status = Status.ACTIVE
        ))
        memberRepository.save(Member(
            name = "member3",
            email = "member3@gmail.com",
            status = Status.ACTIVE
        ))
    }

    @Test
    fun testsFindById() {
        val res = memberRepository.findById(100)
        assertEquals(Optional.empty<Member>(), res)
    }

    @Test
    fun testsFindByIds() {
        val res = memberRepository.findByIds(listOf(1,2))
        assertEquals("member1", res[0].name)
        assertEquals("member2", res[1].name)
    }

    @Test
    fun testsCreate() {
        val member = Member(
            name = "Willem Veelenturf",
            email = "willem.veelenturf@gmail.com"
        )
        val res = memberRepository.save(member)

        assertEquals("Willem Veelenturf", res.name)
    }


}