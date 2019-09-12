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

    @PatchMapping("/{id}")
    fun updateCategory(@Valid @RequestBody request: Map<String, Any>, @PathVariable id: Long): ResponseEntity<Category>? {
        val jsonRequest = Gson().toJson(request)
        val getPlace = categoryRepository.findById(id)
        logger.info { "updatePlace : ${jsonRequest}" }
        var item = categoryRepository.findById(id)
        var updatedCategory = item.get()
        var updatedCategoryJsonString = Gson().toJson(updatedCategory, Category::class.java)
        var updatedCategoryEntity = Gson().fromJson(updatedCategoryJsonString, Category::class.java)

        if (request["title"] != null) {
            updatedCategoryEntity.title = request["title"] as String
        }
        if (request["description"] != null) {
            updatedCategoryEntity.description = request["description"] as String
        }
        logger.info { "updatePlace : ${updatedCategoryEntity}" }

        return ResponseEntity.ok().body(categoryRepository.save(updatedCategoryEntity))
    }

    @DeleteMapping
    fun purgeCategory(): ResponseEntity<Unit> {
        logger.info { "purgeCategory" }
        categoryRepository.resetPrimaryKey()
        val response = categoryRepository.deleteAll()
        logger.info { "purgeCategory : ${response}" }
        return ResponseEntity.noContent().build()
    }
}
