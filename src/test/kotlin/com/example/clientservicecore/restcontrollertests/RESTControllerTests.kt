package com.example.clientservicecore.restcontrollertests

import com.example.clientservicecore.clientrepository.ClientRepository
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
import java.net.URI

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
    fun getExistingClient() {
        this.mockMvc?.perform(get(URI.create("/clients/find/ГРОЗНЫЙ")))?.andDo(print())?.andExpect(status().isOk())?.andExpect(
                content().json("""{"surname":3,"surname":"ГРОЗНЫЙ","name":"ИВАН",
                    |"secondName":"ВАСИЛЬЕВИЧ","dr":"2119-06-12T21:00:00.000+0000",
                    |"account":"ERR_NO_ACC_REP","status":"PROCESSING_COMPLETE"}))""".trimMargin()))


    }

    @Test
    @Throws(Exception::class)
    fun getAllCliens() {
        this.mockMvc?.perform(get("/clients/listall"))?.andDo(print())?.andExpect(status().isOk())?.andExpect(
                content().json("""[
                    |{"surname":1,"surname":"ИВАНОВ","name":"ИВАН","secondName":"ИВАНОВИЧ",
                    |"dr":"2016-05-31T21:00:00.000+0000","account":"1234-5678-9012",
                    |"status":"PROCESSING_COMPLETE"},
                    |{"surname":2,"surname":"СИДОРОВ","name":"АНТОН","secondName":"ПЕТРОВИЧ",
                    |"dr":"2059-06-05T21:00:00.000+0000","account":"ERR_NO_ACC_REP",
                    |"status":"PROCESSING_COMPLETE"},
                    |{"surname":3,"surname":"ГРОЗНЫЙ","name":"ИВАН","secondName":"ВАСИЛЬЕВИЧ",
                    |"dr":"2119-06-12T21:00:00.000+0000","account":"ERR_NO_ACC_REP",
                    |"status":"PROCESSING_COMPLETE"},
                    |{"surname":4,"surname":"Лавандов","name":"Карим","secondName":"Мафусаилович",
                    |"dr":"1894-07-06T21:00:00.000+0000","account":"ERR_NO_ACC_REP",
                    |"status":"PROCESSING_COMPLETE"},
                    |{"surname":5,"surname":"Мышкин","name":"Афанасий","secondName":"Лавинович",
                    |"dr":"1857-02-11T21:00:00.000+0000","account":"ERR_NO_ACC_REP",
                    |"status":"PROCESSING_COMPLETE"}]""".trimMargin()))
    }



    // write test cases here
}