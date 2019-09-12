package co.nyenjes.safari.safari.repository

import co.nyenjes.safari.safari.model.Agent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface AgentRepository: JpaRepository<Agent, Long> {
    @Modifying
    @Transactional
    //Does not work in production
    @Query("TRUNCATE TABLE safari.agent CASCADE", nativeQuery = true)
    fun resetPrimaryKey()

    fun findAllByOrderByIdAsc(): MutableList<Agent>

    fun getAgentByCategoryId(id: Long): MutableList<Agent>

}
