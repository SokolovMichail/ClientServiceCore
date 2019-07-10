package com.example.clientServiceCore.RESTControllerTests

import com.example.clientServiceCore.ClientRepository.ClientRepository
import com.example.clientServiceCore.RESTController.RESTClientController
import org.hamcrest.Matchers.containsString
import org.mockito.Mockito.`when`
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

@MockBean
val clientRepository:ClientRepository?=null

@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@WebMvcTest
class RESTControllerIntegrationTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Test
    @Throws(Exception::class)
    fun givenEmployees_whenGetEmployees_thenReturnJsonArray() {
        this.mockMvc?.perform(get("/greeting"))?.andDo(print())?.andExpect(status().isOk())
                ?.andExpect(content().string(containsString("Hello Mock")));
    }

    // write test cases here
}