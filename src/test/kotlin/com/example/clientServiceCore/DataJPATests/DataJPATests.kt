package com.example.clientServiceCore.DataJPATests

import com.example.clientServiceCore.ClientModel.Client
import com.example.clientServiceCore.ClientRepository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class EmployeeRepositoryIntegrationTest {

    @Autowired
    private val clientRepository: ClientRepository? = null

    // write test cases here

}