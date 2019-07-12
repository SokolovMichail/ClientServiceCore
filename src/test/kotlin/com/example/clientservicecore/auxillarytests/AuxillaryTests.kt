package com.example.clientservicecore.auxillarytests

import com.example.clientservicecore.xmltests.DateParse
import com.example.clientservicecore.сlientmodel.ClientDTO
import org.junit.Test

class AuxillaryTests
{
    @Test
    fun assertOKValidityCheck()
    {
        var client = ClientDTO("Каркаров","Фарид",
                "Филимонович", DateParse.parse("01-06-2016"))
        assert(client.checkValidity())
    }
    @Test
    fun assertFailValidityCheck()
    {
        var client = ClientDTO("","Фарид",
                "Филимонович", DateParse.parse("01-06-2016"))
        var client2 = ClientDTO("Hjv","Фарид",
                "Филимонович", DateParse.parse("01-06-2016"),account = "30-4")
        assert(!((client.checkValidity())or (client2.checkValidity())))

    }
}