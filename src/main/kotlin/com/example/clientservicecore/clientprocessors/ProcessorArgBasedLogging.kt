package com.example.clientservicecore.clientprocessors

import com.example.clientservicecore.сlientmodel.ClientDTO
import org.springframework.stereotype.Component

@Component
class ProcessorArgBasedLogging(private val next:AbstractProcessor?=null)
{
    val s:String = "Arg"
    //This processor's function has got a lot of arguments - so let us try arg-based logging!
    fun process(clientDTO: ClientDTO,str:String) {
        println("Processor with Within based logging is doing something with client ${clientDTO.surname}")
        if (next!=null)
            this.next.process(clientDTO)
    }
}