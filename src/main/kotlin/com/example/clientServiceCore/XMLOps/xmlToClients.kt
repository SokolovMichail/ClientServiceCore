package com.example.clientServiceCore.XMLOps

import java.io.FileInputStream
import java.io.StringWriter
import java.text.SimpleDateFormat
import javax.xml.bind.JAXBContext

var DateParse = SimpleDateFormat("dd-MM-yyyy");

fun xmlToClients(file_to_parse: String): MutableList<ClientXML> {
    var inFile = FileInputStream(file_to_parse)
    val jaxbContext2 = JAXBContext.newInstance(Clients::class.java)
    val marshaller = jaxbContext2.createMarshaller()
    var sw = StringWriter()
   var tmp = ClientXML(
            FIO("Акинфеев","Петр","Аркадьевич"), DateParse.parse("20-01-2019"))
    marshaller.marshal(tmp,sw)
    println(sw.toString())
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