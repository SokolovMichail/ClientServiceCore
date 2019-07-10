package com.example.clientservicecore.datajpatests

import com.example.clientservicecore.сlientmodel.Client
import com.example.clientservicecore.сlientmodel.Processing
import com.example.clientservicecore.clientrepository.ClientRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.text.SimpleDateFormat
import kotlin.test.assertEquals
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles



var DateParse = SimpleDateFormat("dd-MM-yyyy");
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner::class)
class ClientRepositoryIntegrationTest {

    @Autowired
    lateinit var repo: ClientRepository

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Test
    fun assertFindExistingEmployeeByid() {
        val expectedClient = Client(3, "ГРОЗНЫЙ", "ИВАН", "ВАСИЛЬЕВИЧ",
                DateParse.parse("2119-06-13"), "ERR_NO_ACC_REP", Processing.PROCESSING_COMPLETE)
        val client = repo.findById(3).get()
        assertEquals(expectedClient.id, client.id)
        assertEquals(expectedClient.name, client.name)
        assertEquals(expectedClient.surname, client.surname)
        assertEquals(expectedClient.secondName, client.secondName)
        assertEquals(expectedClient.account, client.account)
        assertEquals(expectedClient.status, client.status)
    }

    @Test
    fun assertFindNonExistentEmployeeByid() {
        assert(!repo.findById(443).isPresent)

    }

    @Test
    fun assertInsert()
    {
        var newClient= Client()
        newClient.surname = "Фаров"
        entityManager.persist(newClient)
        entityManager.flush()
        val res = repo.findClientBySurname("Фаров")
        assert(res.isPresent)
    }

    @Test
    fun assertDelete()
    {
        var newClient= Client()
        newClient.surname = "Фаров"
        entityManager.persist(newClient)
        entityManager.flush()
        entityManager.remove(repo.findClientBySurname("Фаров").get())
        entityManager.flush()
        val res = repo.findClientBySurname("Фаров")
        assert(!res.isPresent)
    }
}


