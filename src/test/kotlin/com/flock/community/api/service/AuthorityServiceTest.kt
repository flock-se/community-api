package com.flock.community.api.service

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
@Import(BuckarooService::class)
class AuthorityServiceTest {

    @Autowired
    lateinit var authorityService: AuthorityService

    @Test
    fun findAll() {
        val list = authorityService.findAll()
        assertEquals(6, list.size)
    }

}