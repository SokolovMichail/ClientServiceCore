package com.example.clientservicecore.clientprocessors

import org.springframework.stereotype.Component

@Component
class ProcessorExecutionBasedLogging():AbstractProcessor
{
    override val s:String = "Execution"
}