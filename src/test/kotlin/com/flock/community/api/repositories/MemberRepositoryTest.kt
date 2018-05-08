package com.flock.community.api.repositories

import com.flock.community.api.model.Member
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
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
            street = "Test"
        )
        val res = memberRepository.save(member)

        assertEquals("Willem Veelenturf", res.name)
    }


}