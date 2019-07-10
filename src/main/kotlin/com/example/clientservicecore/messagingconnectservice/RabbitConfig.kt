package com.example.clientservicecore.messagingconnectservice

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    @Bean
    fun requests(): Queue {
        return Queue("requests")
    }

    @Bean
    fun replies(): Queue {
        return Queue("replies")
    }

    @Bean
    fun errors():Queue
    {
        return Queue("errors")
    }
}