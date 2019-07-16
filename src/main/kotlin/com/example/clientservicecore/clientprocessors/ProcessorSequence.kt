package com.example.clientservicecore.clientprocessors

import com.example.clientservicecore.сlientmodel.ClientDTO
import org.springframework.stereotype.Component

@Component
class ProcessorSequence(val processorAnnotationBasedLogging: ProcessorAnnotationBasedLogging, val processorExecutionBasedLogging: ProcessorExecutionBasedLogging, val processorWithinBasedLogging: ProcessorWithinBasedLogging)
{
    fun start(clientDTO: ClientDTO)
    {
        processorAnnotationBasedLogging.process(clientDTO)
        processorExecutionBasedLogging.process(clientDTO)
        processorWithinBasedLogging.process(clientDTO)
    }
}