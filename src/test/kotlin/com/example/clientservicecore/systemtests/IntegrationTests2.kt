package com.example.clientservicecore.systemtests

import com.example.clientservicecore.clientrepository.ClientRepository
import mu.KotlinLogging
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.net.URI

@RunWith(SpringRunner::class)
@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IntegrationTests2 {
    private val logger = KotlinLogging.logger {}
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var mockRepo:ClientRepository

    @Test
    fun assertOKAddClient() {
        this.mockMvc.perform(MockMvcRequestBuilders.post(URI.create("/clients/add")).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(
                """{"id":3,"surname":"ГРОЗНЫЙ","na":"ИВАН",
                    |"sendName":"ВАСИЛЬЕВИЧ","dr":"2119-06-12T21:00:00.000+0000",
                    |"account":"ERR_NO_ACC_REP","status":"PROCESSING_COMPLETE"}))""".trimMargin()
        )).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun assertOKDeleteClient() {
        var performedDeletion = false
        Mockito.`when`(mockRepo.deleteClientBySurname("ГРОЗНЫЙ")).then {
            performedDeletion = true;
            logger.info { "OK" }
        }
        this.mockMvc.perform(MockMvcRequestBuilders.post(URI.create("/clients/del")).contentType(MediaType.APPLICATION_JSON).content(
                """{"surname":3,"surname":"ГРОЗНЫЙ","name":"ИВАН",
                    |"secondName":"ВАСИЛЬЕВИЧ","dr":"2119-06-12T21:00:00.000+0000",
                    |"account":"ERR_NO_ACC_REP","status":"PROCESSING_COMPLETE"}))""".trimMargin()
        ))
        assert(performedDeletion)
    }
}