package co.nyenjes.safari.safari.controller

import co.nyenjes.safari.safari.model.Agent
import co.nyenjes.safari.safari.repository.AgentRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/agent")
class AgentController(private val agentRepository: AgentRepository) {

    @GetMapping
    fun getAllAgents(): List<Agent> = agentRepository.findAllByOrderByIdAsc()

    @GetMapping("/{id}")
    fun getAgentById(@PathVariable id: Long): ResponseEntity<Agent> {
        val response = agentRepository.findById(id).map {place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getAgentById : ${response}" }
        return response
    }

    @GetMapping("/category_id/{categoryId}")
    fun getAgentByPlaceId(@PathVariable categoryId: Long): MutableList<Agent> {
        val response = agentRepository.getAgentByCategoryId(categoryId)

        logger.info { "getAgentByCategoryId : ${response}" }
        return response
    }

    @PostMapping
    fun createAgent(@Valid @RequestBody request: Agent): ResponseEntity<Agent> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createAgent : ${jsonRequest}" }
        val response = agentRepository.save(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/batch")
    fun createBatchAgent(@Valid @RequestBody request: List<Agent>): ArrayList<ResponseEntity<Agent>>? {
        val responses: ArrayList<ResponseEntity<Agent>> = ArrayList()
        request.forEach { agent ->
            val jsonRequest = Gson().toJson(agent)
            logger.info { "createBatchAgent : ${jsonRequest}" }
            val response = agentRepository.save(agent)
            responses.add(ResponseEntity.ok(response))
        }
        return responses
    }

    @DeleteMapping
    fun purgeAgent(): ResponseEntity<Unit> {
        logger.info { "purgeAgent" }
        agentRepository.resetPrimaryKey()
        val response = agentRepository.deleteAll()
        logger.info { "purgeAgent : ${response}" }
        return ResponseEntity.noContent().build()
    }
}
