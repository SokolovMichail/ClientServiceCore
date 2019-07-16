package com.example.clientservicecore.clientprocessors

import com.example.clientservicecore.сlientmodel.ClientDTO
import org.springframework.stereotype.Component

@Component
class ProcessorExecutionBasedLogging()
{
    fun process(clientDTO: ClientDTO) {
        println("Procsssor with execution based logging is doing something with client ${clientDTO.surname}")
    }
}