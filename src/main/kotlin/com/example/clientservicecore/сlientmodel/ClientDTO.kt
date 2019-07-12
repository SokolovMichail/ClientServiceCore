package com.example.clientservicecore.сlientmodel

import java.util.*

class ClientDTO(

        var surname: String = "Pass",
        var name: String = "Pass",
        var secondName: String = "Pass",
        var dr: Date = Date(),
        var account: String = ERR_NO_ACC_DET)
{
    fun toClient():Client
    {
        return Client(surname = this.surname,name = this.name,secondName = this.secondName,account = this.account)
    }

}

fun toListClient(clients:MutableList<ClientDTO>):MutableList<Client>
{
    var res = mutableListOf<Client>()
    clients.forEach()
    {
        res.add(it.toClient())
    }
    return res
}