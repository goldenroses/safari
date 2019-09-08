package co.nyenjes.safari.safari.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "people")
class People (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    val name: String? = null,

    @JoinColumn(name = "profile_image_url")
    val profileImageUrl: String? = null,

    @JoinColumn(name = "email")
    val email: String? = null,

    @JoinColumn(name = "service_type_id")
    val serviceTypeId: Int? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "review_id")
    val review: Set<Review>? = null
)
