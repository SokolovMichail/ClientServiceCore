package com.example.clientServiceCore.FileOpsTest

import com.example.clientServiceCore.XMLOps.ClientXML
import com.example.clientServiceCore.XMLOps.FIO
import com.example.clientServiceCore.XMLOps.xmlToClients
import org.springframework.boot.test.context.SpringBootTest
import java.text.SimpleDateFormat
import kotlin.test.assertEquals

var DateParse = SimpleDateFormat("dd-MM-yyyy");

@SpringBootTest
class FileOpsTest
{
    fun assertOKUnmarshalling()
    {
        var okFilePath = "D:\\Центр-Инвест\\Для Тестирования\\testOK.xml"
        var tmp = xmlToClients(okFilePath)
        var expectedResult = mutableListOf<ClientXML>(ClientXML(
                FIO("Акинфеев","Петр","Аркадьевич"), DateParse.parse("20-01-2019")))
        assertEquals(tmp,expectedResult)
        println("Test OK")
    }
}