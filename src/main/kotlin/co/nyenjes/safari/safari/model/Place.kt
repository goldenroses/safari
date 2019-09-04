package co.nyenjes.safari.safari.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "place", schema = "safari")
class Place (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    @JsonProperty("title")
    var title: String? = null,

    @Column(columnDefinition="text")
    @JsonProperty("descriptionn")
    var description: String? = null,

    @JsonProperty("card_image")
    var cardImage: String? = null,

    @JsonProperty("image_url")
    var imageUrl: String? = null,

    @Column(columnDefinition="text")
    @JsonProperty("content")
    var content: String? = null,

    @ManyToOne(fetch = EAGER )
    @JoinColumn(name = "category_id")
    @JsonProperty("category_id")
    var category: Category? = null

//    @JsonManagedReference
//    @OneToMany(fetch = EAGER )
//    @JoinColumn(name = "review_id")
//    @JsonProperty("review_id")
//    var review: Set<Review>? = HashSet<Review>()
)
