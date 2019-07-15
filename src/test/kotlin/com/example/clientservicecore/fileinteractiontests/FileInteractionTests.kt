package com.example.clientservicecore.fileinteractiontests

import com.example.clientservicecore.clientrepository.ClientRepository
import com.example.clientservicecore.fileops.DirectoryFileHandler
import com.example.clientservicecore.сlientmodel.Client
import org.apache.commons.io.FileUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Files

const val SOURCE_DIR = "D:\\sometestingdirectory"
const val ARCH_DIR = "D:\\somearchive"
const val ERR_DIR = "D:\\somerror"
const val SOURCE_OK_FILE = "D:\\Центр-Инвест\\Для Тестирования\\testing.xml"
const val SOURCE_ERR_FILE = "D:\\Центр-Инвест\\Для Тестирования\\testError.xml"
const val SOURCE_ERR_FILE_NOT_XML = "D:\\Центр-Инвест\\Для Тестирования\\111.jpg"

val sourceDirOK = FileSystems.getDefault().getPath(SOURCE_OK_FILE)
val sourceTestDirOK = FileSystems.getDefault().getPath(SOURCE_DIR + "\\" +"testing.xml" )
val destinationDirOK = FileSystems.getDefault().getPath(ARCH_DIR + "\\" +"testing.xml" )
val sourceDirErr = FileSystems.getDefault().getPath(SOURCE_ERR_FILE)
val sourceTestDirErr = FileSystems.getDefault().getPath(SOURCE_DIR + "\\" +"testError.xml" )
val errorDirFail  = FileSystems.getDefault().getPath(ERR_DIR + "\\" +"testError.xml" )
val sourceDirErrNotXml = FileSystems.getDefault().getPath(SOURCE_ERR_FILE_NOT_XML)
val sourceTestDirErrNotXML = FileSystems.getDefault().getPath(SOURCE_DIR + "\\" +"111.jpg" )
val errorDirFailNotXml  = FileSystems.getDefault().getPath(ERR_DIR + "\\" +"111.jpg" )
@RunWith(SpringRunner::class)
class FileInteractionTests
{

    fun cleanAllDirectories()
    {
        FileUtils.cleanDirectory(File(SOURCE_DIR));
        FileUtils.cleanDirectory(File(ARCH_DIR));
        FileUtils.cleanDirectory(File(ERR_DIR));
    }

    @MockBean
    lateinit var repo:ClientRepository

    @Test
    fun assertOKFileInteractionTests()
    {
        var counter = 0
        cleanAllDirectories()
        Files.copy(sourceDirOK,sourceTestDirOK)
        Mockito.`when`(repo.save(any(Client::class.java))).then { counter+=1; print("Ok");  Client() }
        DirectoryFileHandler(repo).processFile(File(SOURCE_DIR,"testing.xml"))
        assert(counter == 4)
        assert(Files.exists(destinationDirOK))
        cleanAllDirectories()

    }
    @Test
    fun assertFailFileInteractionTests()
    {
        cleanAllDirectories()
        Files.copy(sourceDirErr, sourceTestDirErr)
        Mockito.`when`(repo.save(any(Client::class.java))).then {  Client() }
        DirectoryFileHandler(repo).processFile(File(SOURCE_DIR,"testError.xml"))
        assert(Files.exists(errorDirFail))
        cleanAllDirectories()
    }
    @Test
    fun assertFailNotXMLFileInteractionTests()
    {
        cleanAllDirectories()
        Files.copy(sourceDirErrNotXml, sourceTestDirErrNotXML)
        Mockito.`when`(repo.save(any(Client::class.java))).then {  Client() }
        DirectoryFileHandler(repo).processFile(File(SOURCE_DIR,"111.jpg"))
        assert(Files.exists(errorDirFailNotXml))
        cleanAllDirectories()
    }
}
