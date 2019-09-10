package co.nyenjes.safari

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.ApplicationPidFileWriter
import java.util.*
import javax.annotation.PostConstruct
import com.fasterxml.jackson.databind.ObjectMapper



@EntityScan("co.nyenjes.safari.*")
@SpringBootApplication
class SafariApp
    @PostConstruct
    fun main(args: Array<String>) {

        val objectMapper = ObjectMapper()
        objectMapper.findAndRegisterModules()

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        val springApplication = SpringApplication(SafariApp::class.java)
        springApplication.addListeners(ApplicationPidFileWriter())     // register PID write to spring boot. It will write PID to file
        springApplication.run(*args)
    }
