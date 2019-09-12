package co.nyenjes.safari.safari.model

import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "review")
class Review(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,

    @Column(name = "title")
    val title: String? = null,

    @Column(name = "rating")
    val rating: Int? = null,

    @Column(name = "comment")
    val comment: String? = null,

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "person_id")
    val person: People? = null,

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "place_id")
    var place: Place? = null
    )
