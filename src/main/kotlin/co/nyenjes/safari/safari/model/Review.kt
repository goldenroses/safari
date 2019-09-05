package co.nyenjes.safari.safari.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.persistence.FetchType.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "review")
class Review(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,
    @JsonProperty("rating")
    val rating: Int? = null,

    @JsonProperty("comment")
    val comment: String? = null,

    @JsonProperty("place_id")
    val placeId: Int? = null,

    @JsonProperty("person_id" )
    val personId: Int? = null,

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "person_id")
    @JsonProperty("person")
    val person: People? = null,

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "place_id")
    @JsonProperty("place")
    var place: Place? = null
    )
