package co.nyenjes.safari.safari.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "user")
class User (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    @JsonProperty("name") val name: String? = null,
    @JsonProperty("profile_image_url") val profileImageUrl: String? = null
)
