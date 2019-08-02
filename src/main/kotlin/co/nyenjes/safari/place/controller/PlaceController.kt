package co.nyenjes.safari.place.controller

import co.nyenjes.safari.place.model.Place
import co.nyenjes.safari.place.repository.PlaceRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/place")
class PlaceController(private val placeRepository: PlaceRepository) {

    @GetMapping
    fun getAllPlaces(): List<Place> = placeRepository.findAll()

    @PostMapping
    fun createPlace(@Valid @RequestBody request: Place): ResponseEntity<Place> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createPlace : ${jsonRequest}" }
        val response = placeRepository.save(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/batch")
    fun createBatchPlace(@Valid @RequestBody request: List<Place>): ArrayList<ResponseEntity<Place>>? {

        val responses: ArrayList<ResponseEntity<Place>> = ArrayList()
        request.forEach { place ->
            val jsonRequest = Gson().toJson(place)
            logger.info { "createBatchPlace : ${jsonRequest}" }
            val response = placeRepository.save(place)
            responses.add(ResponseEntity.ok(response))
        }

        return responses
    }

    @DeleteMapping
    fun purgePlace(): ResponseEntity<Unit> {
        logger.info { "purgePlace" }
        placeRepository.resetPrimaryKey()
        val response = placeRepository.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
