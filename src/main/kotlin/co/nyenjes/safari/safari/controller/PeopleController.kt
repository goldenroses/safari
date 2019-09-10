package co.nyenjes.safari.safari.controller

import co.nyenjes.safari.safari.model.People
import co.nyenjes.safari.safari.repository.PeopleRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/people")
class PeopleController(private val reviewRepository: PeopleRepository) {

    @GetMapping
    fun getAllPlaces(): List<People> = reviewRepository.findAllByOrderByIdAsc()

    @PostMapping
    fun createPeople(@Valid @RequestBody request: People): ResponseEntity<People> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createPeople : ${jsonRequest}" }
        val response = reviewRepository.save(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/batch")
    fun createBatchPeople(@Valid @RequestBody request: List<People>): ArrayList<ResponseEntity<People>>? {

        val responses: ArrayList<ResponseEntity<People>> = ArrayList()
        request.forEach { review ->
            val jsonRequest = Gson().toJson(review)
            logger.info { "createBatchPeople : ${jsonRequest}" }
            val response = reviewRepository.save(review)
            responses.add(ResponseEntity.ok(response))
        }
        return responses
    }

    @DeleteMapping
    fun purgePeople(): ResponseEntity<Unit> {
        logger.info { "purgePeople" }
        reviewRepository.resetPrimaryKey()
        val response = reviewRepository.deleteAll()
        logger.info { "purgePeople : ${response}" }
        return ResponseEntity.noContent().build()
    }
}
