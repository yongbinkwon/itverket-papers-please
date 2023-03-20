package no.itverket.papersplease.immigration.immigrant.immigranttype.visa

import no.itverket.papersplease.immigration.immigrant.Immigrant
import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplication
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import no.itverket.papersplease.immigration.immigrationapplication.credentials.visa.Visa
import no.itverket.papersplease.immigration.immigrationapplication.credentials.visa.VisaCredentials
import no.itverket.papersplease.immigration.kafka.producer.dto.ImmigrantDto
import no.itverket.papersplease.immigration.kafka.producer.dto.VisaDto
import no.itverket.papersplease.immigration.registry.CompanyRegistry
import no.itverket.papersplease.immigration.registry.RegistryEntry
import java.util.*

class VisaImmigrant(
    private val employer: String = listOf("konsulent as", "salg inc", "ledelse corp").random(),
    private val employeeId: UUID = UUID.randomUUID(),
    private val registryStrategy: CompanyRegistryStrategy? = null,
    face: UUID = UUID.randomUUID(),
    image: UUID = face,
    processId: UUID,
    immigrationDay: ImmigrationDay,
    expectedResult: ImmigrationApplicationResult,
) : Immigrant(face, image, processId, immigrationDay, expectedResult) {

    companion object {
        private fun validRegistry(employeeId: UUID, company: String) =
            CompanyRegistry(employeeId = employeeId, company = company)

        fun visaImmigrant(processId: UUID, immigrationDay: ImmigrationDay) = VisaImmigrant(
            registryStrategy = this::validRegistry,
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.VISA_IMMIGRANT
        )

        fun unregisteredVisaImmigrant(processId: UUID, immigrationDay: ImmigrationDay) = VisaImmigrant(
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.UNREGISTERED_VISA_IMMIGRANT
        )

        fun invalidEmployer(processId: UUID, immigrationDay: ImmigrationDay) = VisaImmigrant(
            employer = listOf("sykehuset", "kiwi", "NTNU").random(),
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.INVALID_EMPLOYER
        )

        fun invalidImage(processId: UUID, immigrationDay: ImmigrationDay) = VisaImmigrant(
            face = UUID.randomUUID(),
            image = UUID.randomUUID(),
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.INVALID_VISA_IMAGE
        )
    }

    override fun immigrantDto(): ImmigrantDto = ImmigrantDto(
        face = face,
        visa = VisaDto(
            employer = employer,
            employeeId = employeeId,
            image = image
        )
    )

    override fun immigrationApplication() = ImmigrationApplication(
        processId = processId,
        expectedResult = expectedResult,
        face = face,
        immigrationDay = immigrationDay,
        credentials = VisaCredentials(visa())
    )

    private fun visa() = Visa(employer = employer, employeeId = employeeId, image = image)

    override fun registryEntry(): RegistryEntry? = registryStrategy?.let { it(employeeId, employer) }
}

private typealias CompanyRegistryStrategy = (UUID, String) -> CompanyRegistry