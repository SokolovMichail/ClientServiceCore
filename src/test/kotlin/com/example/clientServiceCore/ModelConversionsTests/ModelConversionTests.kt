package com.example.clientServiceCore.ModelConversionsTests

import com.example.clientServiceCore.ClientModel.Client
import com.example.clientServiceCore.XMLOps.ClientXML
import com.example.clientServiceCore.XMLOps.FIO
import com.example.clientServiceCore.XMLtests.DateParse
import org.junit.Test
import kotlin.test.assertEquals

class ModelConversionTests {
    @Test
    fun assertClientXMLtoClientTransformation() {
        val clientConverted = ClientXML(
                FIO("ИВАНОВ", "ИВАН", "ИВАНОВИЧ"), DateParse.parse("01-06-2016")).transformToClient()
        val expectedClient = Client(0, "ИВАНОВ", "ИВАН", "ИВАНОВИЧ", DateParse.parse("01-06-2016"))
        assertEquals(expectedClient.account, "ERR_NO_ACC_DET")
        assertEquals(expectedClient.name, clientConverted.name)
        assertEquals(expectedClient.surname, clientConverted.surname)
        assertEquals(expectedClient.secondName, clientConverted.secondName)
        assertEquals(expectedClient.dr, clientConverted.dr)
    }
}