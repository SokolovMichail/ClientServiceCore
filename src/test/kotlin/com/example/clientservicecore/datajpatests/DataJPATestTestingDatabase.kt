package com.example.clientservicecore.datajpatests

import com.example.clientservicecore.clientrepository.ClientRepository
import com.example.clientservicecore.сlientmodel.Client
import com.example.clientservicecore.сlientmodel.Processing
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.text.SimpleDateFormat
import kotlin.test.assertEquals



var DateParse = SimpleDateFormat("dd-MM-yyyy");
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(SpringRunner::class)
class ClientRepositoryIntegrationTest {

    @Autowired
    lateinit var repo: ClientRepository

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Test
    fun assertFindExistingEmployeeBySurname() {
        val expectedClient = Client(3, "ГРОЗНЫЙ", "ИВАН", "ВАСИЛЬЕВИЧ",
                DateParse.parse("2119-06-13"), "ERR_NO_ACC_REP", Processing.PROCESSING_COMPLETE)
        val client = repo.findClientBySurname("ГРОЗНЫЙ").get()
        assertEquals(expectedClient.id, client.id)
        assertEquals(expectedClient.name, client.name)
        assertEquals(expectedClient.surname, client.surname)
        assertEquals(expectedClient.secondName, client.secondName)
        assertEquals(expectedClient.account, client.account)
        assertEquals(expectedClient.status, client.status)
    }

    @Test
    fun assertFindNonExistentEmployeeBySurname() {
        assert(!repo.findClientBySurname("JKJK").isPresent)

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


