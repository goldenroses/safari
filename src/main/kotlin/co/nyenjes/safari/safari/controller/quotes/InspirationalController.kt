package co.nyenjes.safari.safari.controller.quotes

import co.nyenjes.safari.safari.model.quote.Inspirational
import co.nyenjes.safari.safari.repository.quote.InspirationalRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/quote/inspirational")
class InspirationalController(private val inspirationalRepository: InspirationalRepository) {

    @GetMapping
    fun getAllInspirationalQuotes(): List<Inspirational> = inspirationalRepository.findAllByOrderByIdAsc()

    @PostMapping
    fun createinspirationalQuote(@Valid @RequestBody request: Inspirational): ResponseEntity<Inspirational> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createInspirationalQuote : ${jsonRequest}" }
        val response = inspirationalRepository.save(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/batch")
    fun createBatchinspirationalQuote(@Valid @RequestBody request: List<Inspirational>): ArrayList<ResponseEntity<Inspirational>>? {

        val responses: ArrayList<ResponseEntity<Inspirational>> = ArrayList()
        request.forEach { quote ->
            val jsonRequest = Gson().toJson(quote)
            logger.info { "createBatchInspirationalQuote : ${jsonRequest}" }
            val response = inspirationalRepository.save(quote)
            responses.add(ResponseEntity.ok(response))
        }
        return responses
    }

    @DeleteMapping
    fun purgeinspirationalQuote(): ResponseEntity<Unit> {
        logger.info { "purgeInspirationalQuote" }
        inspirationalRepository.resetPrimaryKey()
        val response = inspirationalRepository.deleteAll()
        logger.info { "purgeInspirationalQuote : ${response}" }
        return ResponseEntity.noContent().build()
    }
}
