package com.example.clientServiceCore.RESTController

import com.example.clientServiceCore.ClientModel.Client
import com.example.clientServiceCore.ClientRepository.ClientRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

internal class ClientNotFoundException(id: Int?) : RuntimeException("Could not find employee " + id!!)

//A service class to implement remote deletion
class Id_Getter(var id:String="")
{
}

@RestController
@RequestMapping("/clients")
class RESTClientController(
        @Autowired val repo: ClientRepository
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
                .orElseThrow { ClientNotFoundException(id) }
    }

    @PostMapping("/del")
    fun delSingleClient(@RequestBody idg:Id_Getter){
        logger.info("Attempted Deletion")
        val p = idg.id.toInt()
        repo.deleteById(p)
    }

}
