package com.flock.community

import ch.qos.logback.core.net.server.Client
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.http.ResponseEntity



@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class CreateClientIntegrationTest(private val restTemplate: TestRestTemplate) {

    @Test
    fun createClient() {
        val responseEntity = restTemplate.postForEntity("/api/donate", "Hallo", String::class.java)
        val client = responseEntity.getBody()
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode())
        assertEquals("Foo", client)
    }
}