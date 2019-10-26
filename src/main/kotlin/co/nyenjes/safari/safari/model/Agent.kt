package co.nyenjes.safari.safari.model
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "agent")
class Agent (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    var title: String? = null,

    @Column(columnDefinition="text")
    @JoinColumn(name = "introduction")
    var introduction: String? = null,

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "person_id")
    val person: People? = null,

    @ManyToOne(fetch = EAGER )
    @JoinColumn(name = "category_id")
    var category: Category? = null,

    @OneToMany(fetch = EAGER )
    @JoinColumn(name = "review_id")
    var review: List<Review>? = ArrayList<Review>()
)
