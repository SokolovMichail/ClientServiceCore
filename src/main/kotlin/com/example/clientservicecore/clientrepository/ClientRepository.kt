package com.example.clientservicecore.clientrepository

import com.example.clientservicecore.сlientmodel.Client
import com.example.clientservicecore.сlientmodel.Processing
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository:JpaRepository<Client,Int>
{
    fun findClientBySurname(name:String):Optional<Client>
    fun findClientsByStatus(sop:Processing):Optional<List<Client>>
    fun deleteClientBySurname(surname:String)
}