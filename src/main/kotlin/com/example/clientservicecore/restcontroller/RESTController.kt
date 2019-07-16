package com.example.clientservicecore.restcontroller

import com.example.clientservicecore.clientprocessors.ProcessorSequence
import com.example.clientservicecore.clientrepository.ClientRepository
import com.example.clientservicecore.сlientmodel.ClientDTO
import com.example.clientservicecore.сlientmodel.toListClientDTO
import mu.KotlinLogging
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


internal class ClientNotFoundException(id: String?) : RuntimeException("Could not find client " + id!!)

//A service class to implement remote deletion
class SurnameGetter(var surname: String = "") {
}

@RestController
@RequestMapping("/clients")
class RESTClientController(
        val repo: ClientRepository,
        var mailSender: JavaMailSender,
        val processorSequence:ProcessorSequence
) {


    private val logger = KotlinLogging.logger {}

    @GetMapping("/listall")
    fun getAllClients(): List<ClientDTO> {
        logger.info("Requested all clients")
        return toListClientDTO(repo.findAll())


    }

    fun sendEmailNotification(clientDTO: ClientDTO,destAdress:String = "sokolovm88@yandex.ru"):String
    {
        val message = SimpleMailMessage()
        message.setTo(destAdress)
        message.setSubject("New VIP")
        message.setText("New vip has joined! This is ${clientDTO.surname},${clientDTO.name}")
        this.mailSender.send(message)
        return "Mail sent"
    }

    @PostMapping("/add")
    fun addSingleClient(@RequestBody clientDTO: ClientDTO)
    {
        if  (clientDTO.checkValidity()) {
            if (clientDTO.vip){
                sendEmailNotification(clientDTO)
            }
            repo.save(clientDTO.toClient())
            logger.info("Saved a new client")
        }
        else
        {
            logger.error { "Client did not pass validity check" }
        }
    }

    @GetMapping("/find/{surname}")
    fun getSingleClientBySurname(@PathVariable surname: String): ClientDTO {
        logger.info("Attempted to find element by surname")
        var tmp = repo.findClientBySurname(surname)
        if (tmp.isPresent){
            processorSequence.start(tmp.get().toClientDto())
            return tmp.get().toClientDto()}
        else
            throw ClientNotFoundException(surname)
    }

    @PostMapping("/del")
    @Transactional
    fun delSingleClient(@RequestBody idg: SurnameGetter) {
        logger.info("Attempted Deletion of client by surname " + idg.surname)
        repo.deleteClientBySurname(idg.surname)
    }
}
