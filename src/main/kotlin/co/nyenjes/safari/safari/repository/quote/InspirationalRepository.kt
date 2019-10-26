package co.nyenjes.safari.safari.repository.quote

import co.nyenjes.safari.safari.model.quote.Inspirational
import co.nyenjes.safari.safari.model.quote.Love
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface InspirationalRepository: JpaRepository<Inspirational, Long> {
    @Modifying
    @Transactional
    //Does not work in production
    @Query("TRUNCATE TABLE safari.inspirational CASCADE", nativeQuery = true)
    fun resetPrimaryKey()

    fun findAllByOrderByIdAsc(): MutableList<Inspirational>

}
