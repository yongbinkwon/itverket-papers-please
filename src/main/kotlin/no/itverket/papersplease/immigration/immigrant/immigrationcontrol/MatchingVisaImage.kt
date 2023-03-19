package no.itverket.papersplease.immigration.immigrant.immigrationcontrol

import no.itverket.papersplease.immigration.immigrant.Immigrant
import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplication
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import no.itverket.papersplease.immigration.immigrationapplication.credentials.visa.Visa
import no.itverket.papersplease.immigration.immigrationapplication.credentials.visa.VisaCredentials
import no.itverket.papersplease.immigration.kafka.dto.ImmigrantDto
import no.itverket.papersplease.immigration.kafka.dto.PassportDto
import java.util.*

class MatchingVisaImage private constructor(
    private val face: UUID,
    processId: UUID,
    immigrationDay: ImmigrationDay,
    expectedResult: ImmigrationApplicationResult
) : Immigrant(processId, immigrationDay, expectedResult) {

    constructor(processId: UUID, immigrationDay: ImmigrationDay) : this(
        face = UUID.randomUUID(),
        processId = processId,
        immigrationDay = immigrationDay,
        expectedResult = ImmigrationApplicationResult.MATCHING_VISA_IMAGE
    )

    override fun result(): ImmigrantDto = ImmigrantDto(
        face = face,
        passport = PassportDto(image = face)
    )

    override fun immigrationProcess() = ImmigrationApplication(
        processId = processId,
        expectedResult = expectedResult,
        face = face,
        immigrationDay = immigrationDay,
        credentials = VisaCredentials(Visa(image = face))
    )
}