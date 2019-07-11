package com.example.clientservicecore.systemtests

import com.example.clientservicecore.clientrepository.ClientRepository
import com.example.clientservicecore.restcontroller.SurnameGetter
import org.junit.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpMethod
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.web.client.exchange
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@RunWith(SpringRunner::class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SystemTests {

    @LocalServerPort
    private val port: Int = 0
    var restTemplate = TestRestTemplate()
    var headers = HttpHeaders()


    @Test
    fun assertOKGetClient() {
        val entity = HttpEntity<String>(null, headers)
        val response = restTemplate.exchange(
                createURLWithPort("clients/find/ГРОЗНЫЙ"), HttpMethod.GET, entity, String::class.java)
        val expected = "{\n" +
                "  \"id\": 3,\n" +
                "  \"surname\": \"ГРОЗНЫЙ\",\n" +
                "  \"name\": \"ИВАН\",\n" +
                "  \"secondName\": \"ВАСИЛЬЕВИЧ\",\n" +
                "  \"dr\": \"2119-06-12T21:00:00.000+0000\",\n" +
                "  \"account\": \"ERR_NO_ACC_REP\",\n" +
                "  \"status\": \"PROCESSING_COMPLETE\"\n" +
                "}"
        JSONAssert.assertEquals(expected, response.body, true)
    }
    @Test
    fun assertOKGetAllClients(){
        val entity = HttpEntity<String>(null, headers)
        val response = restTemplate.exchange(
                createURLWithPort("clients/listall"), HttpMethod.GET, entity, String::class.java)
        val expected = """[{"id":1,"surname":"ИВАНОВ","name":"ИВАН","secondName":"ИВАНОВИЧ",
            |"dr":"2016-05-31T21:00:00.000+0000",
            |"account":"1234-5678-9012",
            |"status":"PROCESSING_COMPLETE"},
            |{"id":2,"surname":"СИДОРОВ","name":"АНТОН","secondName":"ПЕТРОВИЧ",
            |"dr":"2059-06-05T21:00:00.000+0000",
            |"account":"ERR_NO_ACC_REP","status":"PROCESSING_COMPLETE"},
            |{"id":3,"surname":"ГРОЗНЫЙ","name":"ИВАН","secondName":"ВАСИЛЬЕВИЧ",
            |"dr":"2119-06-12T21:00:00.000+0000",
            |"account":"ERR_NO_ACC_REP","status":"PROCESSING_COMPLETE"},
            |{"id":4,"surname":"Лавандов","name":"Карим","secondName":"Мафусаилович",
            |"dr":"1894-07-06T21:00:00.000+0000",
            |"account":"ERR_NO_ACC_REP","status":"PROCESSING_COMPLETE"},
            |{"id":5,"surname":"Мышкин","name":"Афанасий","secondName":"Лавинович",
            |"dr":"1857-02-11T21:00:00.000+0000",
            |"account":"ERR_NO_ACC_REP","status":"PROCESSING_COMPLETE"}]""".trimMargin()
        JSONAssert.assertEquals(expected, response.body, true)
    }
    //TODO
    @Test
    fun assertOKCreateClient()
    {

    }
    //TODO

    @Test
    fun assertOKDeleteClient()
    {
        headers.contentType = MediaType.APPLICATION_JSON_UTF8
        val entity = HttpEntity(SurnameGetter("Мышкин"),headers)
        val URL_SERVICE = "http://localhost:8080/clients/"
        val client = restTemplate.exchange(
                createURLWithPort("clients/del"),HttpMethod.POST,entity, SurnameGetter::class.java)
        println(client.statusCodeValue)
        //assert(client.statusCodeValue == 200)
    }
    @Test
    fun assertFailGetClient()
    {
        val entity = HttpEntity<String>(null, headers)
        val response = restTemplate.exchange(
                createURLWithPort("clients/find/5783490"), HttpMethod.GET, entity, String::class.java)
        print(response.statusCodeValue)
        assert(response.statusCodeValue == 404 )  }
    //TODO
    @Test
    fun assertFailCreateClient()
    {

    }
    //TODO
    @Test
    fun assertFailDeleteClient()
    {

    }

    private fun createURLWithPort(uri: String): String {
        return "http://localhost:$port$uri"
    }
}