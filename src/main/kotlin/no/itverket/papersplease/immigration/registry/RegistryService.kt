package no.itverket.papersplease.immigration.registry

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class RegistryService(
    private val companyRegistryRepository: CompanyRegistryRepository,
    private val foreignerRegistryRepository: ForeignerRegistryRepository,
    private val nationalRegistryRepository: NationalRegistryRepository
) {
    @Transactional(readOnly = true)
    fun employeeExists(employeeId: UUID, company: String) =
        companyRegistryRepository.existsByEmployeeIdAndCompany(employeeId, company)

    @Transactional(readOnly = true)
    fun foreignerExists(ssn: UUID, nationality: String) =
        foreignerRegistryRepository.existsBySsnAndNationality(ssn, nationality)

    @Transactional(readOnly = true)
    fun residentExists(ssn: UUID) =
        nationalRegistryRepository.existsBySsn(ssn)
}