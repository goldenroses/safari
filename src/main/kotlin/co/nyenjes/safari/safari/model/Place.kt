package co.nyenjes.safari.safari.model
import com.fasterxml.jackson.annotation.*
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.NotBlank
import javax.swing.text.Position.Bias.Forward



@Entity
@Table(name = "place")
class Place (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    var title: String? = null,

    @Column(columnDefinition="text")
    var description: String? = null,

    var cardImage: String? = null,

    var imageUrl: String? = null,

    @Column(columnDefinition="text")
    var content: String? = null,

    var categoryId: Int? = null,

    var reviewId: Int? = null,

    @ManyToOne(fetch = EAGER )
    @JoinColumn(name = "category_id")
    var category: Category? = null,

    @OneToMany(fetch = EAGER )
    @JoinColumn(name = "review_id")
    var review: List<Review>? = ArrayList<Review>()
)
