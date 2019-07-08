package com.example.clientServiceCore.ClientRepository

import com.example.clientServiceCore.ClientModel.Client
import com.example.clientServiceCore.ClientModel.Processing
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository:JpaRepository<Client,Int>
{
    fun findClientsByAccountEquals(acc:String):Optional<List<Client>>
    fun findClientBySurname(name:String):Optional<Client>
    fun findClientsByStatus(sop:Processing):Optional<List<Client>>
}