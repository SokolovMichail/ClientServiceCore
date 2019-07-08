package com.example.clientServiceCore.XMLOps

import java.io.FileInputStream
import javax.xml.bind.JAXBContext

fun xmlToClients(file_to_parse: String): MutableList<ClientXML> {
    var inFile = FileInputStream(file_to_parse)
    try {
        val jaxbContext = JAXBContext.newInstance(Clients::class.java)
        val unmarshaller = jaxbContext.createUnmarshaller()
        var Clist: MutableList<ClientXML> = mutableListOf<ClientXML>()
        Clist = (unmarshaller.unmarshal(inFile) as Clients).clients_list
        return Clist
    }
    finally {
        inFile.close()
    }
}