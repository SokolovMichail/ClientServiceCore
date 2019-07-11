package com.example.clientservicecore.restcontrollertests

import com.example.clientservicecore.clientrepository.ClientRepository
import com.example.clientservicecore.restcontroller.RESTClientController
import com.example.clientservicecore.сlientmodel.Client
import com.example.clientservicecore.сlientmodel.Processing
import org.junit.Before
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

var DateParse = SimpleDateFormat("yyyy-MM-dd");
//@RunWith(MockitoJUnitRunner::class)
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureMockMvc
@RunWith(SpringRunner::class)
@WebMvcTest(RESTClientController::class)
class RESTControllerIntegrationTest {

    @MockBean
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getFirstPage() {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                //?.andExpect(content().string(containsString("Hello Mock")));
    }

    @Test
    fun getExistingClient() {
        Mockito.`when`(clientRepository.findClientBySurname("ГРОЗНЫЙ")).thenReturn(Optional.of(Client(3, "ГРОЗНЫЙ", "ИВАН", "ВАСИЛЬЕВИЧ",
                DateParse.parse("2119-06-13"), "ERR_NO_ACC_REP", Processing.PROCESSING_COMPLETE)))
        this.mockMvc.perform(get(URI.create("/clients/find/ГРОЗНЫЙ"))).andDo(print()).andExpect(status().isOk()).andExpect(
                content().json("""{"surname":3,"surname":"ГРОЗНЫЙ","name":"ИВАН",
                    |"secondName":"ВАСИЛЬЕВИЧ","dr":"2119-06-12T21:00:00.000+0000",
                    |"account":"ERR_NO_ACC_REP","status":"PROCESSING_COMPLETE"}))""".trimMargin()))


    }

    @Test
    fun getAllClients() {
        Mockito.`when`(clientRepository.findAll()).thenReturn(mutableListOf(
                Client(1, "ГРОЗНЫЙ", "ИВАН", "ВАСИЛЬЕВИЧ",
                        DateParse.parse("2119-06-13"), "ERR_NO_ACC_REP", Processing.PROCESSING_COMPLETE),
                Client(2, "СИДОРОВ", "АНТОН", "ПЕТРОВИЧ",
                        DateParse.parse("2059-06-6"), "ERR_NO_ACC_REP", Processing.PROCESSING_COMPLETE)
        ))
        this.mockMvc.perform(get("/clients/listall")).andDo(print()).andExpect(status().isOk()).andExpect(
                content().json("""[{"id":1,"surname":"ГРОЗНЫЙ","name":"ИВАН","secondName":"ВАСИЛЬЕВИЧ",
                    |"dr":"2119-06-12T21:00:00.000+0000","account":"ERR_NO_ACC_REP",
                    |"status":"PROCESSING_COMPLETE"},
                    |{"id":2,"surname":"СИДОРОВ","name":"АНТОН","secondName":"ПЕТРОВИЧ",
                    |"dr":"2059-06-05T21:00:00.000+0000","account":"ERR_NO_ACC_REP",
                    |"status":"PROCESSING_COMPLETE"}]""".trimMargin()))
    }

}