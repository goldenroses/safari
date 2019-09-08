package co.nyenjes.safari.safari.repository

import co.nyenjes.safari.safari.model.ServiceType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ServiceTypeRepository: JpaRepository<ServiceType, Long> {
    @Modifying
    @Transactional
    @Query("TRUNCATE TABLE service_type", nativeQuery = true)
    fun resetPrimaryKey()

    fun findAllByOrderByIdAsc(): MutableList<ServiceType>
}
