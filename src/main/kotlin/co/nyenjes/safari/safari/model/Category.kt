package co.nyenjes.safari.safari.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "category")
class Category (
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,
    val title: String? = null,
    val description: String? = null
)
