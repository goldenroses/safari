package co.nyenjes.safari.safari.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "review")
class Review (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    @JsonProperty("rating") val rating: String? = null,
    @JsonProperty("place_id") val placeId: Int? = null,
    @JsonProperty("user_id") val userId: Int? = null,
    @JsonProperty("comment") val comment: String? = null
)