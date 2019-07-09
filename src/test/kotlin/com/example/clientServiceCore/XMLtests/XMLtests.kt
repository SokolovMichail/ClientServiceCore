package com.example.clientServiceCore.XMLtests

import com.example.clientServiceCore.XMLOps.ClientXML
import com.example.clientServiceCore.XMLOps.FIO
import com.example.clientServiceCore.XMLOps.xmlToClients
import org.junit.Test
import java.text.SimpleDateFormat
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

var DateParse = SimpleDateFormat("dd-MM-yyyy");


class XMLtests
{

    fun compareClientsXML(expectedClient:ClientXML,client: ClientXML)
    {
        assertEquals(client.dr,expectedClient.dr)
        assertEquals(client.fio.name,expectedClient.fio.name)
        assertEquals(client.fio.surname,expectedClient.fio.surname)
        assertEquals(client.fio.second_name,expectedClient.fio.second_name)
    }

    @Test
    fun `assertOKUnmarshallingSingle`()
    {
        val okFilePath = "D:\\Центр-Инвест\\Для Тестирования\\testOK.xml"
        val result = xmlToClients(okFilePath)
        val expectedResult = mutableListOf<ClientXML>(ClientXML(
                FIO("Акинфеев","Петр","Аркадьевич"), DateParse.parse("20-01-2019")))
        compareClientsXML(result[0],expectedResult[0])
        //println("Test OK")
    }

    @Test
    fun assertOKUnmarshallingMultiple()
    {
        val okFilePath = "D:\\Центр-Инвест\\Для Тестирования\\testing.xml"
        val result = xmlToClients(okFilePath)
        val expectedResult = mutableListOf<ClientXML>(
                ClientXML(
                        FIO("ИВАНОВ","ИВАН","ИВАНОВИЧ"), DateParse.parse("01-06-2016")),
                ClientXML(
                        FIO("ПЕТРОВ","ПЕТР","ЯКОВЛЕВИЧ"), DateParse.parse("20-01-2019")),
                ClientXML(
                        FIO("СИДОРОВ","АНТОН","ПЕТРОВИЧ"), DateParse.parse("06-06-2059")),
                ClientXML(
                        FIO("ГРОЗНЫЙ","ИВАН","ВАСИЛЬЕВИЧ"), DateParse.parse("13-06-2119")))
        for (i in 0..3)
        {
            compareClientsXML(expectedResult[i],result[i])
        }
       // println("Test OK")
    }

    @Test
    fun assertBadUnmarshal()
    {
        val badFilePath = "D:\\Центр-Инвест\\Для Тестирования\\testError.xml"
        val exception = assertFailsWith<javax.xml.bind.UnmarshalException> { xmlToClients(badFilePath) }
    }



}