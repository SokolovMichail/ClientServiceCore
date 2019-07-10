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
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

@MockBean
val clientRepository:ClientRepository?=null

@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureDataJpa
@WebMvcTest
class RESTControllerIntegrationTest {

    @Autowired
    lateinit var repo:ClientRepository

    @Autowired
    private val mockMvc: MockMvc? = null



    @Test
    @Throws(Exception::class)
    fun getFirstPage() {
        this.mockMvc?.perform(get("/"))?.andDo(print())?.andExpect(status().isOk())
                //?.andExpect(content().string(containsString("Hello Mock")));
    }

    @Test
    @Throws(Exception::class)
    fun getClient() {
        this.mockMvc?.perform(get("/clients/3"))?.andDo(print())?.andExpect(status().isOk())
        //?.andExpect(content().string(containsString("Hello Mock")));
    }

    // write test cases here
}