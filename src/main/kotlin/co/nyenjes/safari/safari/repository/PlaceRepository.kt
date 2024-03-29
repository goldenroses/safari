package co.nyenjes.safari.safari.repository

import co.nyenjes.safari.safari.model.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PlaceRepository: JpaRepository<Place, Long> {

    fun findAllByOrderByIdAsc(): MutableList<Place>

    @Modifying
    @Transactional
    //Does not work in production
    @Query("TRUNCATE TABLE safari.place CASCADE", nativeQuery = true)
    fun resetPrimaryKey()

    @Modifying
    @Transactional
    @Query("update Place p set p.imageUrl = ?2 where p.id = ?1")
    fun updateImageUrlPlace(id: Long?, imageUrl: String?): Int

    @Modifying
    @Transactional
    @Query("update Place p set p.cardImage = ?2 where p.id = ?1")
    fun updateCardImageUrlPlace(id: Long?, imageUrl: String?): Int
}
