package co.nyenjes.safari.safari.model

import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.validation.constraints.NotBlank
import javax.persistence.FetchType
import javax.persistence.FetchType.*

@Entity
@Table(name = "place", schema = "safari")
class Place (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    var title: String? = null,

    @Column(columnDefinition="text")
    var description: String? = null,

    var cardImage: String? = null,
    var imageUrl: String? = null,

    @Column(columnDefinition="text")
    var content: String? = null,

    @ManyToOne(fetch = EAGER )
    @JoinColumn(name = "category_id")
    var category: Category? = Category(1)
)
