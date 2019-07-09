package com.example.clientServiceCore.XMLOps

//TODO Remove all auxillary constructors and convert everything to nullable
//All the auxillary constructors are used in tests only!
import com.example.clientServiceCore.ClientModel.Client
import com.sun.xml.internal.ws.api.ha.StickyFeature
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
class FIO(
        @get:XmlAttribute(name = "фамилия")
        val surname: String? = null,
        @get:XmlAttribute(name = "имя")
        val name: String? = null,
        @get:XmlAttribute(name = "отчество")
        val secondName: String? = null
)


@XmlRootElement(name = "Клиент")
@XmlAccessorType(XmlAccessType.FIELD)
class ClientXML(
        @XmlElement(name = "ФИО")
        val fio: FIO? = FIO(),
        @XmlElement(name = "датаРождения", required = true)
        @XmlJavaTypeAdapter(DateAdapter::class)
        val dr: Date? = Date()
) {
    fun transformToClient(): Client {
        val tmpSurname: String
        val tmpName: String
        val tmpSecondName: String
        val tmpDr: Date
        if (this.fio != null) {
            tmpSurname = if ((this.fio).surname != null) (this.fio).surname.toString() else "Pass"
            tmpName = if ((this.fio).name != null) ((this.fio) as FIO).name.toString() else "Pass"
            tmpSecondName = if ((this.fio).secondName != null) (this.fio).secondName else "Pass"
        } else {
            tmpSurname = "Pass"
            tmpName = "Pass"
            tmpSecondName = "Pass"
        }
        tmpDr = if (this.dr != null) this.dr else Date()
        return Client(surname = tmpSurname,
                name = tmpName,
                secondName = tmpSecondName,
                dr = tmpDr
        )
    }
}

@XmlRootElement(name = "Клиенты")
@XmlAccessorType(XmlAccessType.FIELD)
class Clients() {
    @XmlElement(name = "Клиент")
    var clients_list: MutableList<ClientXML> = mutableListOf<ClientXML>()


}

