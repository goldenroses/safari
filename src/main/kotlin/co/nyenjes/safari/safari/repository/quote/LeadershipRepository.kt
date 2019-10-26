package co.nyenjes.safari.safari.repository.quote

import co.nyenjes.safari.safari.model.quote.Leadership
import co.nyenjes.safari.safari.model.quote.Love
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface LeadershipRepository: JpaRepository<Leadership, Long> {
    @Modifying
    @Transactional
    //Does not work in production
    @Query("TRUNCATE TABLE safari.leadership CASCADE", nativeQuery = true)
    fun resetPrimaryKey()

    fun findAllByOrderByIdAsc(): MutableList<Leadership>

}
