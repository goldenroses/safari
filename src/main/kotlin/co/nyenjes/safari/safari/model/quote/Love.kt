package co.nyenjes.safari.safari.model.quote
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "love")
class Love (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,

    @Column(columnDefinition="text")
    @JoinColumn(name = "content")
    var content: String? = null
)
