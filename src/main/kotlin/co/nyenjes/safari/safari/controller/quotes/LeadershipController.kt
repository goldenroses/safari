package co.nyenjes.safari.safari.controller.quotes

import co.nyenjes.safari.safari.model.quote.Leadership
import co.nyenjes.safari.safari.repository.quote.LeadershipRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/quote/leadership")
class LeadershipController(private val leadershipRepository: LeadershipRepository) {

    @GetMapping
    fun getAllLeadershipQuotes(): List<Leadership> = leadershipRepository.findAllByOrderByIdAsc()

    @PostMapping
    fun createleadershipQuote(@Valid @RequestBody request: Leadership): ResponseEntity<Leadership> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createLeadershipQuote : ${jsonRequest}" }
        val response = leadershipRepository.save(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/batch")
    fun createBatchleadershipQuote(@Valid @RequestBody request: List<Leadership>): ArrayList<ResponseEntity<Leadership>>? {

        val responses: ArrayList<ResponseEntity<Leadership>> = ArrayList()
        request.forEach { quote ->
            val jsonRequest = Gson().toJson(quote)
            logger.info { "createBatchLeadershipQuote : ${jsonRequest}" }
            val response = leadershipRepository.save(quote)
            responses.add(ResponseEntity.ok(response))
        }
        return responses
    }

    @DeleteMapping
    fun purgeleadershipQuote(): ResponseEntity<Unit> {
        logger.info { "purgeLeadershipQuote" }
        leadershipRepository.resetPrimaryKey()
        val response = leadershipRepository.deleteAll()
        logger.info { "purgeLeadershipQuote : ${response}" }
        return ResponseEntity.noContent().build()
    }
}
