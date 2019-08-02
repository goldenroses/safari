package co.nyenjes.safari.place.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "place")
class Place (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    @JsonProperty("title") val title: String? = null,
    @JsonProperty("description") val description: String? = null,
    @JsonProperty("image_url") val imageUrl: String? = null,
    @JsonProperty("content") val content: String? = null,
    @JsonProperty("category_id") val categoryId: Int? = null
)
