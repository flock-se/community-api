package com.flock.community.api.service

import com.fasterxml.jackson.databind.node.ObjectNode
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate


@RunWith(SpringRunner::class)
@SpringBootTest
@Import(BuckarooService::class)
open class BuckarooServiceTest {

    @Autowired
    lateinit var buckarooService: BuckarooService

    @Test
    fun createTransaction() {
        val url = buckarooService.createTransaction(10.00, "HAllo", "INGBNL2A")
        System.out.println(url);
    }
}