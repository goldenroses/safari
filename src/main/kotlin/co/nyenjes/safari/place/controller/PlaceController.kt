package co.nyenjes.safari.place.controller

import co.nyenjes.safari.place.model.Place
import co.nyenjes.safari.place.repository.PlaceRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/places")
class PlaceController(private val placeRepository: PlaceRepository) {

    @PostMapping
    fun createPlace(@Valid @RequestBody request: Place): ResponseEntity<Place> {

        val jsonRequest = Gson().toJson(request)
        logger.info { "createPlace : ${jsonRequest}" }
        val response = placeRepository.save(request)


        return ResponseEntity.ok(response)
    }
}