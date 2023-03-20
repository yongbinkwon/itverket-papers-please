package no.itverket.papersplease.immigration.immigrant.immigranttype.passport

import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import no.itverket.papersplease.immigration.registry.NationalRegistry
import no.itverket.papersplease.immigration.registry.RegistryEntry
import java.util.*

class Resident private constructor(
    private val registryStrategy: NationalRegistryStrategy? = null,
    face: UUID = UUID.randomUUID(),
    image: UUID = face,
    nationality: String = "itverkistan",
    ssn: UUID = UUID.randomUUID(),
    processId: UUID,
    immigrationDay: ImmigrationDay,
    expectedResult: ImmigrationApplicationResult,
) : PassportImmigrant(
    nationality, ssn, face, image, processId, immigrationDay, expectedResult
) {
    companion object {
        private fun validRegistry(ssn: UUID) =
            NationalRegistry(ssn = ssn)

        fun resident(processId: UUID, immigrationDay: ImmigrationDay) = Resident(
            registryStrategy = this::validRegistry,
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.RESIDENT
        )

        fun unregisteredResident(processId: UUID, immigrationDay: ImmigrationDay) = Resident(
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.UNREGISTERED_RESIDENT
        )
    }

    override fun registryEntry(): RegistryEntry? = registryStrategy?.let { it(ssn) }
}

private typealias NationalRegistryStrategy = (UUID) -> NationalRegistry