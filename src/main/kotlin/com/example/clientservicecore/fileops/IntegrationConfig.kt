package com.example.clientservicecore.fileops

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.Pollers
import java.io.File

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