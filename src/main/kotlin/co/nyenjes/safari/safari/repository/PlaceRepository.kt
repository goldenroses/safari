package co.nyenjes.safari.safari.repository

import co.nyenjes.safari.safari.model.Category
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
    @Query("ALTER TABLE place AUTO_INCREMENT=1", nativeQuery = true)
    fun resetPrimaryKey()

//    @Modifying
//    @Transactional
//    @Query("update Place p set p.title = ?2, p.description = ?3, p.cardImage = ?4, p.content = ?5, p.category = ?6 where p.id = ?1")
//    fun updatePlace(id: Long?,
//                    title: String?,
//                    description: String?,
//                    cardImage: String?,
//                    content: String?,
//                    category: Category?
//    ): Int

    @Modifying
    @Transactional
    @Query("update Place p set p.imageUrl = ?2 where p.id = ?1")
    fun updateImageUrlPlace(id: Long?, imageUrl: String?): Int

    @Modifying
    @Transactional
    @Query("update Place p set p.cardImage = ?2 where p.id = ?1")
    fun updateCardImageUrlPlace(id: Long?, imageUrl: String?): Int
}
