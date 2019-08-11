package co.nyenjes.safari.place.controller

import co.nyenjes.safari.place.model.Place
import co.nyenjes.safari.place.repository.PlaceRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.data.jpa.repository.Modifying
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import kotlin.collections.ArrayList

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
        return ok(response)
    }

    @PutMapping("/{id}/imageUrl")
    fun updateImageUrlPlace(@Valid @RequestBody imageUrl: String, @PathVariable(value = "id") id: Long): ResponseEntity<Optional<Place>> {
        logger.info { "updateImageUrlPlace : ${imageUrl}" }
        val response = placeRepository.updateImageUrlPlace(id, imageUrl.trimIndent().replace("\n", "").replace("\\s+".toRegex(), " "))

        val getPlaceResponse = placeRepository.findById(id)
        logger.info { "updateImageUrlPlace : response : ${getPlaceResponse}" }

        return ok(getPlaceResponse)
    }

    @PostMapping("/batch")
    fun createBatchPlace(@Valid @RequestBody request: List<Place>): ArrayList<ResponseEntity<Place>>? {

        val responses: ArrayList<ResponseEntity<Place>> = ArrayList()
        request.forEach { place ->
            val jsonRequest = Gson().toJson(place)
            logger.info { "createBatchPlace : ${jsonRequest}" }
            val response = placeRepository.save(place)
            responses.add(ok(response))
        }

        return responses
    }

    @DeleteMapping
    fun purgePlace(): ResponseEntity<Unit> {
        logger.info { "purgePlace" }
        placeRepository.resetPrimaryKey()
        val response = placeRepository.deleteAll()
        return noContent().build()
    }
}
