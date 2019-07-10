package com.example.clientservicecore.ModelConversionsTests

import com.example.clientservicecore.сlientmodel.Client
import com.example.clientservicecore.xmlops.ClientXML
import com.example.clientservicecore.xmlops.FIO
import com.example.clientservicecore.XMLtests.DateParse
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