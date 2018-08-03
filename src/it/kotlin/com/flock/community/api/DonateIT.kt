package com.flock.community.api

import org.apache.catalina.security.SecurityConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@RunWith(SpringRunner::class)
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
open class CreateClientIntegrationTest {


    @Autowired
    lateinit var mvc: MockMvc

    @Test
    @WithMockUser(username = "TestUser", authorities = [ "UserAuthorities.READ" ])
    fun createClient() {

        mvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

 //       val responseEntity = restTemplate.postForEntity("/api/buckaroo/donate", "Hallo", String::class.java)
//        val client = responseEntity.getBody()
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode())
//        assertEquals("Foo", client)
    }
}