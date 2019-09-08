package co.nyenjes.safari.safari.controller

import co.nyenjes.safari.safari.model.Review
import co.nyenjes.safari.safari.repository.ReviewRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/review")
class ReviewController(private val reviewRepository: ReviewRepository) {

    @GetMapping
    fun getAllReviews(): List<Review> = reviewRepository.findAllByOrderByIdAsc()

    @GetMapping("/{id}")
    fun getReviewById(@PathVariable id: Long): ResponseEntity<Review> {
        val response = reviewRepository.findById(id).map {place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getReviewById : ${response}" }
        return response
    }

    @PostMapping
    fun createReview(@Valid @RequestBody request: Review): ResponseEntity<Review> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createReview : ${jsonRequest}" }
        val response = reviewRepository.save(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/batch")
    fun createBatchReview(@Valid @RequestBody request: List<Review>): ArrayList<ResponseEntity<Review>>? {

        val responses: ArrayList<ResponseEntity<Review>> = ArrayList()
        request.forEach { review ->
            val jsonRequest = Gson().toJson(review)
            logger.info { "createBatchReview : ${jsonRequest}" }
            val response = reviewRepository.save(review)
            responses.add(ResponseEntity.ok(response))
        }
        return responses
    }

    @DeleteMapping
    fun purgeReview(): ResponseEntity<Unit> {
        logger.info { "purgeReview" }
        reviewRepository.resetPrimaryKey()
        val response = reviewRepository.deleteAll()
        logger.info { "purgeReview : ${response}" }
        return ResponseEntity.noContent().build()
    }
}
