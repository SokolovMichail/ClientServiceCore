package com.example.clientservicecore.modelconversionstests

import com.example.clientservicecore.xmlops.ClientXML
import com.example.clientservicecore.xmlops.FIO
import com.example.clientservicecore.xmltests.DateParse
import com.example.clientservicecore.сlientmodel.Client
import com.example.clientservicecore.сlientmodel.ClientDTO
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
    @Test
    fun assertClientDTOtoClientTransformation()
    {
        val clientConverted = ClientDTO("ИВАНОВ","ИВАН",
                "ИВАНОВИЧ",dr = DateParse.parse("01-06-2016")).toClient()
        val expectedClient = Client(0, "ИВАНОВ", "ИВАН",
                "ИВАНОВИЧ", DateParse.parse("01-06-2016"))
        assertEquals(expectedClient.account, "ERR_NO_ACC_DET")
        assertEquals(expectedClient.name, clientConverted.name)
        assertEquals(expectedClient.surname, clientConverted.surname)
        assertEquals(expectedClient.secondName, clientConverted.secondName)
        assertEquals(expectedClient.dr, clientConverted.dr)
    }
//    @Test
//    fun assertClienttoClientDTOTransformation()
//    {
//        val expectedClient = ClientDTO("ИВАНОВ","ИВАН","ИВАНОВИЧ",dr = DateParse.parse("01-06-2016"))
//        val clientConverted = Client(0, "ИВАНОВ", "ИВАН", "ИВАНОВИЧ", DateParse.parse("01-06-2016")).toClientDto()
//        assertEquals(expectedClient.account, "ERR_NO_ACC_DET")
//        assertEquals(expectedClient.name, clientConverted.name)
//        assertEquals(expectedClient.surname, clientConverted.surname)
//        assertEquals(expectedClient.secondName, clientConverted.secondName)
//        assertEquals(expectedClient.dr, clientConverted.dr)
//    }
//    @Test
//    fun assertListClienttoListClientDTOTransformation()
//    {
//
//    }
}