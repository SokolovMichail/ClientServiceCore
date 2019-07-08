package com.example.clientServiceCore.ClientModel

import com.example.clientServiceCore.XMLOps.ClientXML
import java.util.*
import javax.persistence.*

const val ERR_NO_ACC_DET = "ERR_NO_ACC_DET"

enum class Processing {
    AWAIT_PROCESSING, PROCESSING,PROCESSING_COMPLETE
}

@Entity(name = "clients_table") // This tells Hibernate to make a table out of this class
//@SequenceGenerator(surname="seq", initialValue=1, allocationSize=1000000)
@Access(AccessType.FIELD)
class Client(

        @Id
        //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int? = null,
        var surname: String? = "Pass",
        var name: String? = "Pass",
        var second_name: String? = "Pass",
        var dr: Date? = Date(),
        var account: String = ERR_NO_ACC_DET,
        @field:Enumerated(EnumType.STRING)
        var status:Processing = Processing.AWAIT_PROCESSING)
//stateofprocessing variations
//0 - Not yet processed
//1 - Processing started, awaiting response
//2 - Processing finished
{


    fun transformToClientXML(): ClientXML {
        var C = ClientXML()
        C.fio?.name = this.name
        C.fio?.surname = this.surname
        C.fio?.second_name = this.second_name
        C.dr = this.dr
        return C
    }

    fun niceview(): String {
        return this.name + " " + this.surname
    }

}