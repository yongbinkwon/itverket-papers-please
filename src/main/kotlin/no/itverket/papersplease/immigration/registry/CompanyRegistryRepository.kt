package no.itverket.papersplease.immigration.registry

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CompanyRegistryRepository: JpaRepository<CompanyRegistry, Long> {
    fun existsByEmployeeIdAndCompany(employeeId: UUID, company: String): Boolean
}