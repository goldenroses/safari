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
    @Query("ALTER TABLE place AUTO_INCREMENT=1", nativeQuery = true)
    fun resetPrimaryKey()

    @Modifying
    @Transactional
    @Query("update Place p set p.imageUrl = ?2 where p.id = ?1")
    fun updateImageUrlPlace(placeId: Long?, imageUrl: String?): Int

    @Modifying
    @Transactional
    @Query("update Place p set p.cardImage = ?2 where p.id = ?1")
    fun updateCardImageUrlPlace(placeId: Long?, imageUrl: String?): Int
}
