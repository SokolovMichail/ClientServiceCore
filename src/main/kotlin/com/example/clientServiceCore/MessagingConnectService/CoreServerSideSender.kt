package com.example.clientServiceCore.MessagingConnectService

import com.example.clientServiceCore.ClientModel.Processing
import com.example.clientServiceCore.ClientRepository.ClientRepository
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
@Component
class CoreServerSideSender(@Autowired
                           val repo: ClientRepository,
                           @Autowired
                           private val template: RabbitTemplate)
{
    @Scheduled(fixedDelay = 60000, initialDelay = 10000)
    fun send() {
        val c = repo.findClientsByStatus(Processing.AWAIT_PROCESSING)
        if (c.isPresent) {
            c.get().forEach()
            {
                it.status = Processing.PROCESSING
                repo.save(it)
                this.template.convertAndSend("requests", it.surname.toString())
                logger.info { "Sent a message to ClientServerAccountService, contents = ${it.surname}" }
            }
        } else {
            logger.info { "All account entries OK" }
        }
    }
}

