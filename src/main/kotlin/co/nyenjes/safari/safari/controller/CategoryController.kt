package co.nyenjes.safari.safari.controller
import co.nyenjes.safari.safari.model.Category
import co.nyenjes.safari.safari.repository.CategoryRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/category")
class CategoryController(private val categoryRepository: CategoryRepository) {

    @GetMapping
    fun getAllPlaces(): List<Category> = categoryRepository.findAllByOrderByIdAsc()

    @PostMapping
    fun createCategory(@Valid @RequestBody request: Category): ResponseEntity<Category> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createCategory : ${jsonRequest}" }
        val response = categoryRepository.save(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/batch")
    fun createBatchCategory(@Valid @RequestBody request: List<Category>): ArrayList<ResponseEntity<Category>>? {

        val responses: ArrayList<ResponseEntity<Category>> = ArrayList()
        request.forEach { category ->
            val jsonRequest = Gson().toJson(category)
            logger.info { "createBatchCategory : ${jsonRequest}" }
            val response = categoryRepository.save(category)
            responses.add(ResponseEntity.ok(response))
        }

        return responses
    }

    @DeleteMapping
    fun purgeCategory(): ResponseEntity<Unit> {
        logger.info { "purgeCategory" }
        categoryRepository.resetPrimaryKey()
        val response = categoryRepository.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
