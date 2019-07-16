package com.example.clientservicecore.clientprocessors

import com.example.clientservicecore.сlientmodel.ClientDTO
import org.springframework.stereotype.Component

//Just a placeholder process
@Component
class ProcessorAnnotationBasedLogging()
{
    @MyLoggableAnnotation
    fun process(clientDTO: ClientDTO) {
        println("Processor with annotation based logging is doing something with client ${clientDTO.surname}")
    }
}