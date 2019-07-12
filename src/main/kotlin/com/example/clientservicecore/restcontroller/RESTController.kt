package com.example.clientservicecore.restcontroller

import com.example.clientservicecore.clientrepository.ClientRepository
import com.example.clientservicecore.сlientmodel.ClientDTO
import com.example.clientservicecore.сlientmodel.toListClientDTO
import mu.KotlinLogging
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


internal class ClientNotFoundException(id: String?) : RuntimeException("Could not find client " + id!!)

//A service class to implement remote deletion
class SurnameGetter(var surname: String = "") {
}

@RestController
@RequestMapping("/clients")
class RESTClientController(
        val repo: ClientRepository
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/listall")
    fun getAllClients(): List<ClientDTO> {
        logger.info("Requested all clients")
        return toListClientDTO(repo.findAll())


    }

    @PostMapping("/add")
    fun addSingleClient(@RequestBody clientDTO: ClientDTO) {

        repo.save(clientDTO.toClient())
        logger.info("Saved a new client")

    }


    @GetMapping("/find/{surname}")
    fun getSingleClientBySurname(@PathVariable surname: String): ClientDTO {
        logger.info("Attempted to find element by surname")
        var tmp = repo.findClientBySurname(surname)
        if (tmp.isPresent)
            return tmp.get().toClientDto()
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
