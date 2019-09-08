package co.nyenjes.safari.safari.repository

import co.nyenjes.safari.safari.model.People
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PeopleRepository: JpaRepository<People, Long> {
    @Modifying
    @Transactional
    @Query("TRUNCATE TABLE review", nativeQuery = true)
    fun resetPrimaryKey()

    fun findAllByOrderByIdAsc(): MutableList<People>
}
