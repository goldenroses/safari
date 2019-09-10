package co.nyenjes.safari.safari.model

import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "service_type")
class ServiceType(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,
    var title: String? = null,

    var description: String? = null,

    @OneToMany(fetch = EAGER )
    @JoinColumn(name = "person_id")
    var people: Set<People>? = HashSet<People>()
)
