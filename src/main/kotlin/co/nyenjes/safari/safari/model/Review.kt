package co.nyenjes.safari.safari.model

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.annotation.JsonCreator.Mode.DEFAULT
import javax.persistence.*
import javax.persistence.FetchType.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "review")
class Review(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,

    @Column(name = "rating")
    val rating: Int? = null,

    @Column(name = "comment")
    val comment: String? = null,

    val placeId: Int? = null,

    val personId: Int? = null,

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "person_id")
    val person: People? = null,

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "place_id")
    var place: Place? = null
    )
