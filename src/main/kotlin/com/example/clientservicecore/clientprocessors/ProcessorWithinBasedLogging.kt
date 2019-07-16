package com.example.clientservicecore.clientprocessors

import com.example.clientservicecore.сlientmodel.ClientDTO
import org.springframework.stereotype.Component

@Component
class ProcessorWithinBasedLogging()
{
    fun process(clientDTO: ClientDTO) {
        println("Processor with Within based logging is doing something with client ${clientDTO.surname}")
    }
}