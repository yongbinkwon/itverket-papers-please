package no.itverket.papersplease.immigration.immigrant.immigranttype.passport

import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import no.itverket.papersplease.immigration.registry.ForeignerRegistry
import no.itverket.papersplease.immigration.registry.RegistryEntry
import java.util.*

class Foreigner private constructor(
    private val registryStrategy: ForeignerRegistryStrategy? = null,
    face: UUID = UUID.randomUUID(),
    image: UUID = face,
    nationality: String = listOf("omegapointistan", "fire-nation").random(),
    ssn: UUID = UUID.randomUUID(),
    processId: UUID,
    immigrationDay: ImmigrationDay,
    expectedResult: ImmigrationApplicationResult,
) : PassportImmigrant(
    nationality, ssn, face, image, processId, immigrationDay, expectedResult
) {
    companion object {
        private fun validRegistry(ssn: UUID, nationality: String) =
            ForeignerRegistry(ssn = ssn, nationality = nationality)

        fun validForeigner(processId: UUID, immigrationDay: ImmigrationDay) = Foreigner(
            registryStrategy = this::validRegistry,
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.FOREIGNER
        )

        fun unregisteredForeigner(processId: UUID, immigrationDay: ImmigrationDay) = Foreigner(
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.UNREGISTERED_FOREIGNER
        )

        fun invalidNationality(processId: UUID, immigrationDay: ImmigrationDay) = Foreigner(
            processId = processId,
            immigrationDay = immigrationDay,
            nationality = listOf("sopra-land", "netcompanigeria").random(),
            expectedResult = ImmigrationApplicationResult.INVALID_FOREIGN_NATION
        )
    }

    override fun registryEntry(): RegistryEntry? = registryStrategy?.let { it(ssn, nationality) }
}

private typealias ForeignerRegistryStrategy = (UUID, String) -> ForeignerRegistry