package com.example.clientServiceCore.IntegrationTests

import org.junit.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTests {

    @LocalServerPort
    private val port: Int = 0
    internal var restTemplate = TestRestTemplate()
    internal var headers = HttpHeaders()
    //TODO refactor to use a separate database
    @Test
    fun testGetClient() {
        val entity = HttpEntity<String>(null, headers)
        val response = restTemplate.exchange(
                createURLWithPort("/clients/170"), HttpMethod.GET, entity, String::class.java)
        val expected = "{\n" +
                "  \"id\": 170,\n" +
                "  \"surname\": \"ГРОЗНЫЙ\",\n" +
                "  \"name\": \"ИВАН\",\n" +
                "  \"secondName\": \"ВАСИЛЬЕВИЧ\",\n" +
                "  \"dr\": \"2119-06-12T21:00:00.000+0000\",\n" +
                "  \"account\": \"ERR_NO_ACC_REP\",\n" +
                "  \"status\": \"PROCESSING_COMPLETE\"\n" +
                "}"
        JSONAssert.assertEquals(expected, response.body, false)
    }

    private fun createURLWithPort(uri: String): String {
        return "http://localhost:$port$uri"
    }
}