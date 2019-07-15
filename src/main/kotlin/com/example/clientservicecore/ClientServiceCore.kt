package com.example.clientservicecore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
class ClientServiceCore

fun main(args: Array<String>) {
    runApplication<ClientServiceCore>(*args)

}

