package com.example.clientServiceCore.RESTController

import com.example.clientServiceCore.ClientModel.Client
import com.example.clientServiceCore.ClientRepository.ClientRepository
import mu.KotlinLogging
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat

internal class ClientNotFoundException(id: Int?) : RuntimeException("Could not find employee " + id!!)
//A service class to implement remote deletion
//I have not found an option to do it another way...
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
        var res = repo.findAll()
        return res
    }
    @PostMapping("/add")
    fun addSingleClient(@RequestBody client: Client)
    {
        println(client.dr)
        try {
            repo.save(client)
            logger.info("Saved a new client")
        }
        catch (e:Exception)
        {
            logger.error(e.message)
            throw e
        }

        //return "Ok"
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
        var p = idg.id.toInt()
        repo.deleteById(p)
       // return "Attempted deletion"
    }

}
