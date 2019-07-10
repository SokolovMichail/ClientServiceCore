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
        var status:Processing = Processing.AWAIT_PROCESSING)
{
}