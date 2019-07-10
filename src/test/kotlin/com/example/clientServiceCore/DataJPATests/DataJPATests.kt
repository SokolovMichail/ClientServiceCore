package com.example.clientServiceCore.DataJPATests

import com.example.clientServiceCore.ClientModel.Client
import com.example.clientServiceCore.ClientModel.Processing
import com.example.clientServiceCore.ClientRepository.ClientRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.text.SimpleDateFormat
import kotlin.test.assertEquals

var DateParse = SimpleDateFormat("dd-MM-yyyy");
@TestPropertySource("testing.properties")
@DataJpaTest
class EmployeeRepositoryIntegrationTest {

    @Autowired
    private val clientRepository: ClientRepository?=null


    @Test
    fun findEmployeeByid() {
        val client = clientRepository?.findById(3)?.get()
        val expectedClient = Client(3,"ГРОЗНЫЙ","ИВАН","ВАСИЛЬЕВИЧ",
                DateParse.parse("2119-06-13"),"ERR_NO_ACC_REP",Processing.PROCESSING_COMPLETE)
        assertEquals(expectedClient.id,client?.id)
        assertEquals(expectedClient.name,client?.name)
        assertEquals(expectedClient.surname,client?.surname)
        assertEquals(expectedClient.secondName,client?.secondName)
        assertEquals(expectedClient.account,client?.account)
        assertEquals(expectedClient.status,client?.status)
    }

}