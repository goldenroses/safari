package co.nyenjes.safari.safari.controller

import co.nyenjes.safari.safari.model.Category
import co.nyenjes.safari.safari.model.Place
import co.nyenjes.safari.safari.model.requests.ImageRequest
import co.nyenjes.safari.safari.repository.PlaceRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import mu.KotlinLogging
import org.json.JSONObject
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ExecutionException
import javax.validation.Valid
import kotlin.collections.ArrayList
import com.fasterxml.jackson.databind.ObjectMapper



private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/place")
class PlaceController(private val placeRepository: PlaceRepository) {

    @GetMapping
    fun getAllPlaces(): MutableList<Place> = placeRepository.findAllByOrderByIdAsc()

    @GetMapping("/{id}")
    fun getPlaceById(@PathVariable id: Long): ResponseEntity<Place> {
        val response = placeRepository.findById(id).map {place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getPlaceById : ${response}" }
        return response
    }

    @PostMapping
    fun createPlace(@Valid @RequestBody request: Place): ResponseEntity<Place> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createPlace : ${jsonRequest}" }
        val response = placeRepository.save(request)
        logger.info { "createPlace : ${response}" }
        return ok(response)
    }

    @PatchMapping("/{id}")
    fun updatePlace(@Valid @RequestBody request: Map<String, Any>, @PathVariable id: Long): ResponseEntity<Place>? {
        val jsonRequest = Gson().toJson(request)
        val getPlace = placeRepository.findById(id)
        logger.info { "updatePlace : ${getPlace}" }
        var item = placeRepository.findById(id)
        var updatedPlace = item.get()
        var updatedPlaceJsonString = Gson().toJson(updatedPlace, Place::class.java)
        var updatedPlaceEntity = Gson().fromJson(updatedPlaceJsonString, Place::class.java)

        if (request["title"] != null) {
            updatedPlaceEntity.title = request["title"] as String
        }
        if (request["description"] != null) {
            updatedPlaceEntity.description = request["description"] as String
        }
        if (request["cardImage"] != null) {
            updatedPlaceEntity.cardImage = request["cardImage"] as String
        }
        if (request["imageUrl"] != null) {
            updatedPlaceEntity.imageUrl = request["imageUrl"] as String
        }
        if (request["content"] != null) {
            updatedPlaceEntity.content = request["content"] as String
        }
        if (request["category"] != null) {
            val updatedCatJsonString = Gson().fromJson(request["category"].toString(), Category::class.java)

            if (updatedCatJsonString.id != null) {
                updatedPlaceEntity.category?.id = updatedCatJsonString.id
            }
            if (updatedCatJsonString.title != null) {
                updatedPlaceEntity.category?.title = updatedCatJsonString.title
            }
            if (updatedCatJsonString.description != null) {
                updatedPlaceEntity.category?.description = updatedCatJsonString.description
            }
        }
        logger.info { "updatePlace : ${updatedPlaceEntity}" }

        return ResponseEntity.ok().body(placeRepository.save(updatedPlaceEntity))
    }

    @PatchMapping("/{id}/imageUrl")
    fun updateImageUrlPlace(@Valid @RequestBody request: String, @PathVariable(value = "id") id: Long): ResponseEntity<Optional<Place>> {
        logger.info { "updateImageUrlPlace : ${request}" }
        val response = placeRepository.updateImageUrlPlace(id, request)
        val getPlaceResponse = placeRepository.findById(id)
        logger.info { "updateImageUrlPlace : response : ${getPlaceResponse}" }

        return ok(getPlaceResponse)
    }

    @PutMapping("/{id}/card/bucketName")
    fun updateCardImagePlace(@Valid @RequestBody bucketName: String, @PathVariable(value = "id") id: Long): ResponseEntity<Optional<Place>>? {
        logger.info { "updateCardImagePlace : ${bucketName}" }
        val response = placeRepository.updateCardImageUrlPlace(id, bucketName)
        logger.info { "updateImageUrlPlace : response : ${response}" }

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
            throw Exception("People Not Authenticated")
        } catch (e: ExecutionException) {
            throw Exception("People Not Authenticated")
        }

        return userId
    }
}
