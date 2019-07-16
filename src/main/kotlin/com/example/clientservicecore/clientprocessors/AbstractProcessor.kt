package com.example.clientservicecore.clientprocessors

import com.example.clientservicecore.сlientmodel.ClientDTO

interface AbstractProcessor
{
    open val s:String

    fun process(clientDTO: ClientDTO) {
        println("Processor with $s based logging is doing something with client ${clientDTO.surname}")
    }
}


