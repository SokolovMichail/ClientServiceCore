package com.example.clientservicecore.restcontroller

import com.example.clientservicecore.сlientmodel.Client
import com.example.clientservicecore.clientrepository.ClientRepository
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

internal class ClientNotFoundException(id: String?) : RuntimeException("Could not find employee " + id!!)

//A service class to implement remote deletion
class SurnameGetter(var surname:String="")
{
}

@RestController
@RequestMapping("/clients")
class RESTClientController(
        val repo: ClientRepository
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/listall")
    fun getAllClients(): List<Client> {
        logger.info("Requested all clients")
        return repo.findAll()
    }
    @PostMapping("/add")
    fun addSingleClient(@RequestBody client: Client)
    {
        try {
            repo.save(client)
            logger.info("Saved a new client")
        }
        catch (e:Exception)
        {
            logger.error(e.message)
            throw e
        }
    }

    @GetMapping("/{id}")
    fun getSingleClient(@PathVariable id: Int):Client {
        logger.info("Attempted to find element by id")
        return repo.findById(id)
                .orElseThrow { ClientNotFoundException(id.toString()) }
    }

    @GetMapping("/find/{surname}")
    fun getSingleClientBySurname(@PathVariable surname: String):Client {
        logger.info("Attempted to find element by surname")
        return repo.findClientBySurname(surname)
                .orElseThrow { ClientNotFoundException(surname) }
    }

    @PostMapping("/del")
    fun delSingleClient(@RequestBody idg:SurnameGetter){
        logger.info("Attempted Deletion")
        repo.deleteClientBySurname(idg.surname)
    }

}
