package com.example.clientServiceCore.MessagingConnectService

import com.example.clientServiceCore.ClientModel.Processing
import com.example.clientServiceCore.ClientRepository.ClientRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
@RabbitListener(queues = ["replies"])
class CoreServerSideReceiver(@Autowired val repo: ClientRepository) {

    var gson = Gson()

    @RabbitHandler
    fun receive(inp: String) {
        var received = gson.fromJson<AccountMessage>(inp, AccountMessage::class.java)
        logger.info { "Received a message ${inp}" }
        val clientFoundBySurname = repo.findClientBySurname(received.surname)
        if (clientFoundBySurname.isPresent) {
            val client = clientFoundBySurname.get()
            if (client.status == Processing.PROCESSING) {
                client.account = received.account
                client.status = Processing.PROCESSING_COMPLETE
                repo.save(client)
                logger.info {
                    "Successfully saved new account setting to client with id ${client.id}"
                }
            } else {
                logger.error {"Account information conflict"}
            }
        } else {
            logger.info { "Failed to find a client with surname ${received.surname}" }
        }
    }
}

