package co.nyenjes.safari.place.repository

import co.nyenjes.safari.place.model.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PlaceRepository: JpaRepository<Place, Long> {
    @Modifying
    @Transactional
    @Query("ALTER TABLE Place AUTO_INCREMENT=1", nativeQuery = true)
    fun resetPrimaryKey()
}
