package co.nyenjes.safari.place.repository

import co.nyenjes.safari.place.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
    @Modifying
    @Transactional
    @Query("ALTER TABLE Place AUTO_INCREMENT=1", nativeQuery = true)
    fun resetPrimaryKey()
}
