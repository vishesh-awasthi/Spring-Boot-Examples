package com.vishesh.springbootkotlin.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.StatusResultMatchersDsl

@SpringBootTest
@AutoConfigureMockMvc
internal class AccountControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun shouldReturnAllAccounts() {
        mockMvc.get("/api/accounts")
            .andDo { print() }
            .andExpect {
                status(StatusResultMatchersDsl::isOk)
                content {
                    contentType(APPLICATION_JSON)
                }
                jsonPath("$[0].accountNumber") { value(3143112323) }
                jsonPath("$[1].accountNumber") { value(5425324234) }
                jsonPath("$[2].accountNumber") { value(5432532134) }
            }
    }
}