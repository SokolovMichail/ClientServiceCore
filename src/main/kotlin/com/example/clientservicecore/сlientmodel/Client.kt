package com.example.clientservicecore.сlientmodel

import java.util.*
import javax.persistence.*

const val ERR_NO_ACC_DET = "ERR_NO_ACC_DET"

enum class Processing {
    AWAIT_PROCESSING, PROCESSING,PROCESSING_COMPLETE
}
@Entity(name = "clients_table")
@Access(AccessType.FIELD)
class Client(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int? = null,
        var surname: String = "Pass",
        var name: String = "Pass",
        var secondName: String = "Pass",
        var dr: Date = Date(),
        var account: String = ERR_NO_ACC_DET,
        @field:Enumerated(EnumType.STRING)
        var status:Processing = Processing.AWAIT_PROCESSING,
        var vip:Boolean = false)
{
    fun toClientDto():ClientDTO
    {
        return ClientDTO(this.surname,this.name,this.secondName,this.dr,this.account,vip = this.vip)
    }
}

fun toListClientDTO(clients:MutableList<Client>):MutableList<ClientDTO>
{
    var res = mutableListOf<ClientDTO>()
    clients.forEach()
    {
        res.add(it.toClientDto())
    }
    return res
}