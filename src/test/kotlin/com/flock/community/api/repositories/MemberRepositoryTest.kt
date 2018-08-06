package com.flock.community.api.repositories

import com.flock.community.api.model.Member
import com.flock.community.api.model.MemberGroup
import com.flock.community.api.model.Status
import junit.framework.Assert.assertEquals
import org.junit.Ignore
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
                firstName = "member1",
                surName = "member1",
                email = "member1@gmail.com",
                status = Status.ACTIVE
        ))
        memberRepository.save(Member(
                firstName = "member2",
                surName = "member2",
                email = "member2@gmail.com",
                status = Status.ACTIVE
        ))
        memberRepository.save(Member(
                firstName = "member3",
                surName = "member3",
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
    @Ignore
    fun testsFindByIds() {
        val res = memberRepository.findByIds(listOf(1, 2))
        assertEquals("member1", res[0].firstName)
        assertEquals("member2", res[1].firstName)
    }

    @Test
    fun testsCreate() {
        val member = Member(
                firstName = "Willem",
                surName = "Veelenturf",
                email = "willem.veelenturf@gmail.com"
        )
        val res = memberRepository.save(member)

        assertEquals("Willem", res.firstName)
    }

    @Test
    fun testsGroup() {

        val group = MemberGroup(
                code = "LEKSTREEK",
                name = "Lekstreek"
        )
        val member1 = Member(
                firstName = "Willem",
                surName = "Veelenturf",
                email = "willem.veelenturf@gmail.com1",
                groups = setOf(group)
        )
        val res1 = memberRepository.save(member1)

        assertEquals("Willem", res1.firstName)
        assertEquals("LEKSTREEK", res1.groups.toList()[0].code)

        val member2 = Member(
                firstName = "Willem",
                surName = "Veelenturf",
                email = "willem.veelenturf@gmail.com2",
                groups = setOf(group)
        )
        val res2 = memberRepository.save(member2)

        assertEquals("Willem", res2.firstName)
        assertEquals("LEKSTREEK", res2.groups.toList()[0].code)


    }


}