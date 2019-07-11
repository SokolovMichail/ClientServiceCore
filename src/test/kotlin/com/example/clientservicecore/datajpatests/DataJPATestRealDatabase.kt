package com.example.clientservicecore.datajpatests

import com.example.clientservicecore.clientrepository.ClientRepository
import com.example.clientservicecore.сlientmodel.Client
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner


@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner::class)
class ClientRepositoryRealIntegrationTest {

    @Autowired
    lateinit var repo: ClientRepository

    @Autowired
    lateinit var entityManager: TestEntityManager


    @Test
    fun assertFindNonExistentEmployeeByid() {
        assert(!repo.findById(0).isPresent)

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


