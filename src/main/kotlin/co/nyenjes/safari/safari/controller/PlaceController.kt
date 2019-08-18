package co.nyenjes.safari.safari.controller

import co.nyenjes.safari.safari.model.Place
import co.nyenjes.safari.safari.model.requests.ImageRequest
import co.nyenjes.safari.safari.repository.PlaceRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ExecutionException
import javax.validation.Valid
import kotlin.collections.ArrayList

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/place")
class PlaceController(private val placeRepository: PlaceRepository) {

    @GetMapping
    fun getAllPlaces(): MutableList<Place> = placeRepository.findAll()

    @GetMapping("/{id}")
    fun getPlaceById(@PathVariable id: Long): ResponseEntity<Place> {
        return placeRepository.findById(id).map {place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createPlace(@Valid @RequestBody request: Place): ResponseEntity<Place> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createPlace : ${jsonRequest}" }
        val response = placeRepository.save(request)
        return ok(response)
    }

    @PutMapping("/{id}/imageUrl")
    fun updateImageUrlPlace(@Valid @RequestBody request: ImageRequest, @PathVariable(value = "id") id: Long): ResponseEntity<Optional<Place>> {
        logger.info { "updateImageUrlPlace : ${request}" }
        val jsonRequest = Gson().toJson(request)
        val response = placeRepository.updateImageUrlPlace(id, jsonRequest)
        val getPlaceResponse = placeRepository.findById(id)
        logger.info { "updateImageUrlPlace : response : ${getPlaceResponse}" }

        return ok(getPlaceResponse)
    }

    @PutMapping("/{id}/card/imageUrl")
    fun updateCardImagePlace(@Valid @RequestBody imageUrl: String, @PathVariable(value = "id") id: Long): ResponseEntity<Optional<Place>>? {
        logger.info { "updateCardImagePlace : ${imageUrl}" }
        val response = placeRepository.updateCardImageUrlPlace(id, imageUrl.trimIndent().replace("\n", "").replace("\\s+".toRegex(), " "))

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

    //Firebase Image functionality
    @PostMapping("/firebase/image")
    fun uploadImageToFirebase(@RequestBody requestBody: ImageRequest, @RequestHeader(value = "FIREBASE_TOKEN") firebaseToken: String): Any {
        //service token

        // idToken comes from the HTTP Header
        val decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(firebaseToken).get()
        val uid = decodedToken.uid

        // process the code here
        // once it is done
        logger.info { "uploadImageToFirebase : ${uid}" }

        return uid

    }

    fun getUserIdFromIdToken(idToken: String): String? {
        var userId: String? = null
        try {
            userId = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get().uid
        } catch (e: InterruptedException) {
            throw Exception("User Not Authenticated")
        } catch (e: ExecutionException) {
            throw Exception("User Not Authenticated")
        }

        return userId
    }
}
