package co.nyenjes.safari.safari.model

import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.validation.constraints.NotBlank
import javax.persistence.FetchType
import javax.persistence.FetchType.*


@Entity
@Table(name = "place")
class Place (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    var title: String? = null,

    @Column(columnDefinition="TEXT")
    val description: String? = null,

    val cardImage: String? = null,
    val imageUrl: String? = null,

    @Column(columnDefinition="TEXT")
    val content: String? = null,

    @ManyToOne(cascade = [ALL], fetch = EAGER )
    @JoinColumn(name = "categoryId")
    var category: Category? = null
)
