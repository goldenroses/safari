package co.nyenjes.safari.safari

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource
import org.flywaydb.core.Flyway

private val logger = KotlinLogging.logger {}

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "safariTransactionManager", basePackages = arrayOf("co.nyenjes.safari.safari.repository"), entityManagerFactoryRef = "safariEntityManagerFactory")
class PlaceConfig {

    @Bean(name = arrayOf("safariDataSource"))
    @ConfigurationProperties(prefix = "safari.datasource")
    fun dataSource(): DataSource {
//        val flyway = Flyway()
//        flyway.setDataSource(
//            System.getenv("SPRING_DATASOURCE_URL"),
//            System.getenv("SPRING_DATASOURCE_USERNAME"),
//            System.getenv("SPRING_DATASOURCE_PASSWORD")
//        )
//        flyway.migrate()

        val dataSource = DataSourceBuilder.create()

        val ds = dataSource.driverClassName("org.postgresql.Driver").build()
        return ds
    }

    @Bean(name = arrayOf("safariEntityManagerFactory"))
    fun safariEntityManagerFactory(
            builder: EntityManagerFactoryBuilder, @Qualifier("safariDataSource") dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource).packages("co.nyenjes.safari.safari.model").persistenceUnit("safari")
                .build()
    }

    @Bean(name = arrayOf("safariTransactionManager"))
    fun safariTransactionManager(
            @Qualifier("safariEntityManagerFactory") safariEntityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(safariEntityManagerFactory)
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry?) {
                registry!!.addMapping("/**").allowedOrigins(
                    "http://localhost:3000",
                    "https://safari-web.firebaseapp.com"
                    ).allowedMethods("GET", "POST", "PUT", "DELETE")
            }
        }
    }
}
