package com.example.clientservicecore.clientprocessors

import com.example.clientservicecore.сlientmodel.ClientDTO
import org.springframework.stereotype.Component

//Just a placeholder process
@Component
class ProcessorAnnotationBasedLogging():AbstractProcessor
{
    override val s:String = "Annotation"
    @MyLoggableAnnotation
    override fun process(clientDTO: ClientDTO) {
        println("Processor with $s based logging is doing something with client ${clientDTO.surname}")
    }


}



