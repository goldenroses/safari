package co.nyenjes.safari.safari.repository

import co.nyenjes.safari.safari.model.Category
import co.nyenjes.safari.safari.model.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
    @Modifying
    @Transactional
    @Query("TRUNCATE TABLE category", nativeQuery = true)
    fun resetPrimaryKey()

    fun findAllByOrderByIdAsc(): MutableList<Category>
}
