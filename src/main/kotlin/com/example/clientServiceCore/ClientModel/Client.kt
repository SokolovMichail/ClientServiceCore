package com.example.clientServiceCore.ClientModel

import com.example.clientServiceCore.XMLOps.ClientXML
import java.util.*
import javax.persistence.*

const val ERR_NO_ACC_DET = "ERR_NO_ACC_DET"

enum class Processing {
    AWAIT_PROCESSING, PROCESSING,PROCESSING_COMPLETE
}
//TODO Refactor second_name to secondName
@Entity(name = "clients_table")
@Access(AccessType.FIELD)
class Client(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int? = null,
        var surname: String = "Pass",
        var name: String = "Pass",
        var second_name: String = "Pass",
        var dr: Date = Date(),
        var account: String = ERR_NO_ACC_DET,
        @field:Enumerated(EnumType.STRING)
        var status:Processing = Processing.AWAIT_PROCESSING)
{

    fun transformToClientXML(): ClientXML {
        var C = ClientXML()
        C.fio.name = this.name
        C.fio.surname = this.surname
        C.fio.second_name = this.second_name
        C.dr = this.dr
        return C
    }
}