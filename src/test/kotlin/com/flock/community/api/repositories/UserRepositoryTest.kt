package com.flock.community.api.repositories

import com.flock.community.api.model.Member
import com.flock.community.api.model.Status
import com.flock.community.api.model.User
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
open class UserRepositoryTest {


    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun testsSave() {
        userRepository.save(User(
            name = "member1",
            email = "member1@gmail.com",
            reference = "kdjhqif"
        ))

    }



}