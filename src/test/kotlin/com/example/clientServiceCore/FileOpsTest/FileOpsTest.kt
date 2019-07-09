package com.example.clientServiceCore.FileOpsTest

import com.example.clientServiceCore.XMLOps.ClientXML
import com.example.clientServiceCore.XMLOps.FIO
import com.example.clientServiceCore.XMLOps.xmlToClients
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import java.text.SimpleDateFormat
import kotlin.test.assertEquals

var DateParse = SimpleDateFormat("dd-MM-yyyy");


class FileOpsTest
{

    fun compareClientsXML(expectedClient:ClientXML,client: ClientXML)
    {
        assertEquals(client.dr,expectedClient.dr)
        assertEquals(client.fio.name,expectedClient.fio.name)
        assertEquals(client.fio.surname,expectedClient.fio.surname)
        assertEquals(client.fio.second_name,expectedClient.fio.second_name)
    }
    @Test
    fun assertOKUnmarshallingSingle()
    {
        var okFilePath = "D:\\Центр-Инвест\\Для Тестирования\\testOK.xml"
        var result = xmlToClients(okFilePath)
        var expectedResult = mutableListOf<ClientXML>(ClientXML(
                FIO("Акинфеев","Петр","Аркадьевич"), DateParse.parse("20-01-2019")))
        compareClientsXML(result[0],expectedResult[0])
        println("Test OK")
    }

}