package no.itverket.papersplease.immigration.registry

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface NationalRegistryRepository: JpaRepository<NationalRegistry, Long> {
    fun existsBySsn(ssn: UUID): Boolean
}