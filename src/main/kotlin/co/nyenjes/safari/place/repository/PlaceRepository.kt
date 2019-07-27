package co.nyenjes.safari.place.repository

import co.nyenjes.safari.place.model.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository: JpaRepository<Place, Long>
