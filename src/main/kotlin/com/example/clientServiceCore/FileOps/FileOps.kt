package com.example.clientServiceCore.FileOps

import com.example.clientServiceCore.ClientRepository.ClientRepository
import com.example.clientServiceCore.XMLOps.xmlToClients
import mu.KotlinLogging
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.Pollers
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Path

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
        var targetDestination = FileSystems.getDefault().getPath(ARCH_DIR + "\\" +f.name )
        try {
            if (f.absolutePath.endsWith("xml")and(!File(ARCH_DIR+"\\"+f.name).exists())) {
                var cList = xmlToClients(f.absolutePath)
                cList.forEach() {
                    repo.save(it.transformToClient())
                }
                logger.info("Processing OK, saved "+ cList.size + " clients" )

            } else {
                targetDestination = FileSystems.getDefault().getPath(ERR_DIR + "\\" +f.name )
                print(targetDestination)
                logger.error("Processing failed, file is either not XML of faulty " )
            }
        } catch (e: javax.xml.bind.UnmarshalException) {
            targetDestination = FileSystems.getDefault().getPath(ERR_DIR + "\\" +f.name )
            logger.error("Unmarshalling failed")
        } finally {
            Files.move(sourceDir,targetDestination)
            f.delete()
        }
    }
}

@Configuration
class IntegrationFlowProcessor(val hnd: DirectoryFileHandler) {
    @Bean
    fun iflow(): IntegrationFlow {
        return IntegrationFlows.from(
                org.springframework.integration.file.dsl.Files.inboundAdapter(File(SOURCE_DIR)))
        { spec -> spec.poller(Pollers.fixedDelay(500)) }
                .handle(hnd).get()
    }
}