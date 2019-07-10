package com.example.clientServiceCore.DataJPATests

import com.example.clientServiceCore.ClientModel.Client
import com.example.clientServiceCore.ClientModel.Processing
import com.example.clientServiceCore.ClientRepository.ClientRepository
import com.example.clientServiceCore.ClientServiceCore
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.TestPropertySource
import java.text.SimpleDateFormat
import kotlin.test.assertEquals
import com.sun.deploy.util.SearchPath.findOne
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration


var DateParse = SimpleDateFormat("dd-MM-yyyy");
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner::class)
class ClientRepositoryIntegrationTest {

    @Autowired
    lateinit var repo:ClientRepository

    @Test
    fun findEmployeeByid() {
        val expectedClient =   Client(3,"ГРОЗНЫЙ","ИВАН","ВАСИЛЬЕВИЧ",
                DateParse.parse("2119-06-13"),"ERR_NO_ACC_REP",Processing.PROCESSING_COMPLETE)
        val client = repo?.findById(3)?.get()
        assertEquals(expectedClient.id,client?.id)
        assertEquals(expectedClient.name,client?.name)
        assertEquals(expectedClient.surname,client?.surname)
        assertEquals(expectedClient.secondName,client?.secondName)
        assertEquals(expectedClient.account,client?.account)
        assertEquals(expectedClient.status,client?.status)
    }

}


