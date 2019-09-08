package co.nyenjes.safari.safari.controller
import co.nyenjes.safari.safari.model.ServiceType
import co.nyenjes.safari.safari.repository.ServiceTypeRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/service_type")
class ServiceTypeController(private val serviceTypeRepository: ServiceTypeRepository) {

    @GetMapping
    fun getAllPlaces(): List<ServiceType> = serviceTypeRepository.findAllByOrderByIdAsc()

    @PostMapping
    fun createServiceType(@Valid @RequestBody request: ServiceType): ResponseEntity<ServiceType> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createServiceType : ${jsonRequest}" }
        val response = serviceTypeRepository.save(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/batch")
    fun createBatchServiceType(@Valid @RequestBody request: List<ServiceType>): ArrayList<ResponseEntity<ServiceType>>? {

        val responses: ArrayList<ResponseEntity<ServiceType>> = ArrayList()
        request.forEach { serviceType ->
            val jsonRequest = Gson().toJson(serviceType)
            logger.info { "createBatchServiceType : ${jsonRequest}" }
            val response = serviceTypeRepository.save(serviceType)
            responses.add(ResponseEntity.ok(response))
        }

        return responses
    }

    @DeleteMapping
    fun purgeServiceType(): ResponseEntity<Unit> {
        logger.info { "purgeServiceType" }
        serviceTypeRepository.resetPrimaryKey()
        val response = serviceTypeRepository.deleteAll()
        logger.info { "purgeServiceType : ${response}" }
        return ResponseEntity.noContent().build()
    }
}
