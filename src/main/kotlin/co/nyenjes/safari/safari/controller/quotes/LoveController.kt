package co.nyenjes.safari.safari.controller.quotes

import co.nyenjes.safari.safari.model.quote.Love
import co.nyenjes.safari.safari.repository.quote.LoveRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/quote/love")
class loveQuoteController(private val loveRepository: LoveRepository) {

    @GetMapping
    fun getAllloveQuotes(): List<Love> = loveRepository.findAllByOrderByIdAsc()

    @PostMapping
    fun createloveQuote(@Valid @RequestBody request: Love): ResponseEntity<Love> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createloveQuote : ${jsonRequest}" }
        val response = loveRepository.save(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/batch")
    fun createBatchloveQuote(@Valid @RequestBody request: List<Love>): ArrayList<ResponseEntity<Love>>? {

        val responses: ArrayList<ResponseEntity<Love>> = ArrayList()
        request.forEach { quote ->
            val jsonRequest = Gson().toJson(quote)
            logger.info { "createBatchloveQuote : ${jsonRequest}" }
            val response = loveRepository.save(quote)
            responses.add(ResponseEntity.ok(response))
        }
        return responses
    }

    @DeleteMapping
    fun purgeloveQuote(): ResponseEntity<Unit> {
        logger.info { "purgeloveQuote" }
        loveRepository.resetPrimaryKey()
        val response = loveRepository.deleteAll()
        logger.info { "purgeloveQuote : ${response}" }
        return ResponseEntity.noContent().build()
    }
}
