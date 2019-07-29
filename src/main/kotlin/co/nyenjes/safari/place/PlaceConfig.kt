package co.nyenjes.safari.place

import com.zaxxer.hikari.HikariConfig
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

private val logger = KotlinLogging.logger {}

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "safariTransactionManager", basePackages = arrayOf("co.nyenjes.safari.place.repository"), entityManagerFactoryRef = "safariEntityManagerFactory")
class PlaceConfig {

    @Bean(name = arrayOf("safariDataSource"))
    @ConfigurationProperties(prefix = "safari.datasource")
    fun dataSource(): DataSource {
        val dataSource = DataSourceBuilder.create().build()
//        val ds = dataSource.driverClassName("org.postgresql.Driver").build()
        return dataSource
    }

    @Bean(name = arrayOf("safariEntityManagerFactory"))
    fun safariEntityManagerFactory(
            builder: EntityManagerFactoryBuilder, @Qualifier("safariDataSource") dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource).packages("co.nyenjes.safari.place.model").persistenceUnit("place")
                .build()
    }

    @Bean(name = arrayOf("safariTransactionManager"))
    fun safariTransactionManager(
            @Qualifier("safariEntityManagerFactory") safariEntityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(safariEntityManagerFactory)
    }
}