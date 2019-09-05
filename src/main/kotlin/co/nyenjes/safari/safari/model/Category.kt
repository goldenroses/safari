package co.nyenjes.safari.safari.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "category")
class Category(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,
    var title: String? = null,

    var description: String? = null

//    @OneToMany(fetch = EAGER )
//    @JoinColumn(name = "person_id")
//    @JsonProperty("person_id")
//    var people: Set<People>? = HashSet<People>()
)
