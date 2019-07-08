package com.example.clientServiceCore.FileOps

import com.example.clientServiceCore.ClientRepository.ClientRepository
import com.example.clientServiceCore.XMLOps.xmlToClients
import mu.KotlinLogging
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Files
import java.nio.file.FileSystems

const val SOURCE_DIR = "D:\\sometestingdirectory"
const val ARCH_DIR = "D:\\somearchive"
const val ERR_DIR = "D:\\somerror"

@Component
class DirectoryFileHandler(val repo: ClientRepository) {

    private val logger = KotlinLogging.logger {}
    @ServiceActivator
    fun processFile(f: File) {
        logger.info("Attempt to process a file ${f.absolutePath}")
        val sourceDir = FileSystems.getDefault().getPath(f.absolutePath)
        var destinationDir = FileSystems.getDefault().getPath(ARCH_DIR + "\\" +f.name )
        try {
            if (f.absolutePath.endsWith("xml")and(!File(ARCH_DIR+"\\"+f.name).exists())) {
                var cList = xmlToClients(f.absolutePath)
                cList.forEach() {
                    repo.save(it.transformToClient())
                }
                logger.info("Processing OK, saved "+ cList.size + " clients" )

            } else {
                destinationDir = FileSystems.getDefault().getPath(ERR_DIR + "\\" +f.name )
                print(destinationDir)
                logger.error("Processing failed, file is either not XML of faulty " )
            }
        } catch (e: javax.xml.bind.UnmarshalException) {
            destinationDir = FileSystems.getDefault().getPath(ERR_DIR + "\\" +f.name )
            logger.error("Unmarshalling failed")
        } finally {
            Files.move(sourceDir,destinationDir)
            f.delete()
        }
    }
}
