package com.example.clientServiceCore.XMLOps

import com.example.clientServiceCore.ClientModel.Client
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
    var surname: String = "Pass"
    @XmlAttribute(name = "имя")
    var name: String = "Pass"
    @XmlAttribute(name = "отчество")
    var second_name: String = "Pass"

    //Конструктор для теста
    constructor(surname:String,name:String,second_name:String ):this()
    {
        this.name = name
        this.second_name = second_name
        this.surname = surname
    }
}


@XmlRootElement(name = "Клиент")
@XmlAccessorType(XmlAccessType.FIELD)
class ClientXML() {
    @XmlElement(name = "ФИО")
    var fio: FIO = FIO()
    @XmlElement(name = "датаРождения",required = true)
    @XmlJavaTypeAdapter(DateAdapter::class)
    var dr:Date=Date()

    fun transformToClient():Client
    {
        return Client(surname = this.fio.surname,
                        name = this.fio.name,
                        secondName = this.fio.second_name,
                        dr = this.dr
        )
    }
    //Конструктор
    constructor(fio:FIO,dr:Date):this()
    {
        this.fio = fio
        this.dr = dr
    }
}


@XmlRootElement(name = "Клиенты")
@XmlAccessorType(XmlAccessType.FIELD)
class Clients() {
    @XmlElement(name="Клиент")
    var clients_list: MutableList<ClientXML> = mutableListOf<ClientXML>()


}

