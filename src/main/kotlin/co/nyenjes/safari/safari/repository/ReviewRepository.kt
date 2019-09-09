package co.nyenjes.safari.safari.repository

import co.nyenjes.safari.safari.model.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ReviewRepository: JpaRepository<Review, Long> {
    @Modifying
    @Transactional
    //Does not work in production
    @Query("TRUNCATE TABLE safari.review CASCADE", nativeQuery = true)
    fun resetPrimaryKey()

    fun findAllByOrderByIdAsc(): MutableList<Review>
}
