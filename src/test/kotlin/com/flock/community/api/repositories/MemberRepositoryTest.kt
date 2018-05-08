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

@DataJpaTest
@RunWith(SpringRunner::class)
open class MemberRepositoryTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun testsFindById() {
        val res = memberRepository.findById(1)
        assertEquals(Optional.empty<Member>(), res)
    }

    @Test
    fun testsCreate() {
        val member = Member(
            name = "Willem Veelenturf",
            status = Status.ACTIVE
        )
        val res = memberRepository.save(member)

        assertEquals("Willem Veelenturf", res.name)
    }


}