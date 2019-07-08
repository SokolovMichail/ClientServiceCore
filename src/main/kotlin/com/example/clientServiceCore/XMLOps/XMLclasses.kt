package com.example.clientServiceCore.XMLOps

import com.example.clientServiceCore.ClientModel.Client
import java.io.File
import java.io.FileInputStream
import javax.xml.bind.JAXBContext
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlAttribute
import java.text.SimpleDateFormat
import javax.xml.bind.annotation.adapters.XmlAdapter
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter

//Service adapter to read data from xml
class DateAdapter : XmlAdapter<String, Date>() {

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy")

    @Throws(Exception::class)
    override fun marshal(v: Date): String {
        synchronized(dateFormat) {
            return dateFormat.format(v)
        }
    }

    @Throws(Exception::class)
    override fun unmarshal(v: String): Date {
        synchronized(dateFormat) {
            return dateFormat.parse(v)
        }
    }

}

@XmlRootElement(name = "ФИО")
@XmlAccessorType(XmlAccessType.FIELD)
class FIO() {
    @XmlAttribute(name = "фамилия")
    var surname: String? = "Pass"
    @XmlAttribute(name = "имя")
    var name: String? = "Pass"
    @XmlAttribute(name = "отчество")
    var second_name: String? = "Pass"

}


@XmlRootElement(name = "Клиент")
@XmlAccessorType(XmlAccessType.FIELD)
class ClientXML() {
    @XmlElement(name = "ФИО")
    var fio: FIO? = FIO()
    @XmlElement(name = "датаРождения",required = true)
    @XmlJavaTypeAdapter(DateAdapter::class)
    var dr:Date?=Date()

    fun transformToClient():Client
    {
        //??????
        return Client(surname = this.fio?.surname,
                        name = this.fio?.name,
                        second_name = this.fio?.second_name,
                        dr = this.dr
        )
    }

    /*  public fun FormSqlInsertQuery(table: String): String {
          return """INSERT INTO ${table} (surname,surname,second_name,birth_date) VALUES \
           ('${fio.surname}','${fio.surname}','${fio.second_name}','${dr.date}')"""

      }*/
}


@XmlRootElement(name = "Клиенты")
@XmlAccessorType(XmlAccessType.FIELD)
class Clients() {
    @XmlElement(name="Клиент")
    var clients_list: MutableList<ClientXML> = mutableListOf<ClientXML>()


}


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