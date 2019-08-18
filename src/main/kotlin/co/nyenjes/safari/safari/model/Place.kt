package co.nyenjes.safari.safari.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "place")
class Place (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    val title: String? = null,
    val description: String? = null,
    val cardImage: String? = null,
    val imageUrl: String? = null,
    val content: String? = null,
    @ManyToOne
    @JoinColumn(name = "category_id")
    val categoryId: Category? = null
)
