package no.itverket.papersplease.immigration.registry

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ForeignerRegistryRepository: JpaRepository<ForeignerRegistry, Long> {
    fun existsBySsnAndNationality(ssn: UUID, nationality: String): Boolean
}