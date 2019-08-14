package co.nyenjes.safari

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.ApplicationPidFileWriter
import org.springframework.context.annotation.Bean
import java.util.*
import javax.annotation.PostConstruct
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@EntityScan("co.nyenjes.safari.*")
@SpringBootApplication
class SafariApp
    @PostConstruct
    fun main(args: Array<String>) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        val springApplication = SpringApplication(SafariApp::class.java)
        springApplication.addListeners(ApplicationPidFileWriter())     // register PID write to spring boot. It will write PID to file
        springApplication.run(*args)
    }
