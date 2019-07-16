package com.example.clientservicecore.clientprocessors

import org.springframework.stereotype.Component

@Component
class ProcessorWithinBasedLogging():AbstractProcessor
{
    override val s:String = "Within"
}