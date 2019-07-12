package com.example.clientservicecore.systemtests

import com.example.clientservicecore.clientrepository.ClientRepository
import com.example.clientservicecore.restcontroller.SurnameGetter
import com.example.clientservicecore.xmltests.DateParse
import com.example.clientservicecore.сlientmodel.ClientDTO
import org.junit.Test
import org.junit.runner.RunWith
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestTemplate


@RunWith(SpringRunner::class)
@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SystemTests {

    @LocalServerPort
    private val port: Int = 0
    var testrestTemplate = TestRestTemplate()
    var restTemplate = RestTemplate()
    var headers = HttpHeaders()
    var jsonHeaders = HttpHeaders()
    @Autowired
    lateinit var rabbitTemplate: RabbitTemplate
    @Autowired
    lateinit var repo:ClientRepository

    @Test
    fun contextLoads() {

    }

    @Test
    fun rabbitLoadTest()
    {
        assert(rabbitTemplate != null)
    }

    @Test
    fun repositoryLoadTest()
    {
        assert(repo != null)
    }


    @Test
    fun assertOKGetClient() {
        val entity = HttpEntity<String>(null, headers)
        val response = testrestTemplate.exchange(
                createURLWithPort("clients/find/ГРОЗНЫЙ"), HttpMethod.GET, entity, String::class.java)
        val expected = """{"surname": "ГРОЗНЫЙ",
            "name": "ИВАН",
            "secondName": "ВАСИЛЬЕВИЧ",
            "dr": "2119-06-12T21:00:00.000+0000",
            "account": "ERR_NO_ACC_REP"}"""
        JSONAssert.assertEquals(expected, response.body, true)
    }

    @Test
    fun assertOKGetAllClients() {
        val entity = HttpEntity<String>(null, headers)
        val response = testrestTemplate.exchange(
                createURLWithPort("clients/listall"), HttpMethod.GET, entity, String::class.java)
        val expected = """[{"surname":"ИВАНОВ","name":"ИВАН","secondName":"ИВАНОВИЧ",
            |"dr":"2016-05-31T21:00:00.000+0000",
            |"account":"1234-5678-9012"},
            |{"surname":"СИДОРОВ","name":"АНТОН","secondName":"ПЕТРОВИЧ",
            |"dr":"2059-06-05T21:00:00.000+0000",
            |"account":"ERR_NO_ACC_REP"},
            |{"surname":"ГРОЗНЫЙ","name":"ИВАН","secondName":"ВАСИЛЬЕВИЧ",
            |"dr":"2119-06-12T21:00:00.000+0000",
            |"account":"ERR_NO_ACC_REP"},
            |{"surname":"Лавандов","name":"Карим","secondName":"Мафусаилович",
            |"dr":"1894-07-06T21:00:00.000+0000",
            |"account":"ERR_NO_ACC_REP"},
            |{"surname":"Мышкин","name":"Афанасий","secondName":"Лавинович",
            |"dr":"1857-02-11T21:00:00.000+0000",
            |"account":"ERR_NO_ACC_REP"}]""".trimMargin()
        JSONAssert.assertEquals(expected, response.body, true)
    }
    @Test
    fun assertFailGetClient()
    {
        val entity = HttpEntity<String>(null, headers)
        val response = testrestTemplate.exchange(
                createURLWithPort("clients/find/5783490"), HttpMethod.GET, entity, String::class.java)
        print(response.statusCodeValue)
        assert(response.statusCodeValue == 404 )  }

    @Test
    fun assertOKCreateClient()
    {
        jsonHeaders.contentType = MediaType.APPLICATION_JSON_UTF8
        var entity = HttpEntity<ClientDTO>(
                ClientDTO("Фаров","Фарид",
                        "Филимонович", DateParse.parse("01-06-2016")),jsonHeaders)
        val response = restTemplate.exchange(
                createURLWithPort("clients/add"), HttpMethod.POST, entity, String::class.java)
        //print(response.statusCodeValue)
        assert(response.statusCodeValue == 200 )  }

    @Test
    fun assertOKDeleteClient()
    {

        jsonHeaders.contentType = MediaType.APPLICATION_JSON_UTF8
        var entity = HttpEntity<ClientDTO>(
                ClientDTO("Каркаров","Фарид",
                        "Филимонович", DateParse.parse("01-06-2016")),jsonHeaders)
        val response = restTemplate.exchange(
                createURLWithPort("clients/add"), HttpMethod.POST, entity, String::class.java)
        val entityDelete = HttpEntity<SurnameGetter>(SurnameGetter("Каркаров"),jsonHeaders)
        val responseDelete = restTemplate.exchange(
                createURLWithPort("clients/del"), HttpMethod.POST, entityDelete, String::class.java)
        assert(responseDelete.statusCodeValue == 200 )  }


    private fun createURLWithPort(uri: String): String {
        return "http://localhost:$port$uri"
    }
}