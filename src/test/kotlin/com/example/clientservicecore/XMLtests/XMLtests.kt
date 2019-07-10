package com.example.clientservicecore.XMLtests

import com.example.clientservicecore.xmlops.ClientXML
import com.example.clientservicecore.xmlops.FIO
import com.example.clientservicecore.xmlops.xmlToClients
import org.junit.Test
import java.text.SimpleDateFormat
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

var DateParse = SimpleDateFormat("dd-MM-yyyy");


class XMLtests
{
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
            assertEquals(result[i].dr,expectedResult[i].dr)
            assertEquals(result[i].fio?.name,expectedResult[i].fio?.name)
            assertEquals(result[i].fio?.surname,expectedResult[i].fio?.surname)
            assertEquals(result[i].fio?.secondName,expectedResult[i].fio?.secondName)
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