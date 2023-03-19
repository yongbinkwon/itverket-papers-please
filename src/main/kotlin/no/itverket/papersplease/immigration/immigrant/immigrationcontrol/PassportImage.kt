package no.itverket.papersplease.immigration.immigrant.immigrationcontrol

import no.itverket.papersplease.immigration.immigrant.Immigrant
import no.itverket.papersplease.immigration.immigrant.ImmigrantBuilder
import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplication
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import no.itverket.papersplease.immigration.immigrationapplication.credentials.passport.Passport
import no.itverket.papersplease.immigration.immigrationapplication.credentials.passport.PassportCredentials
import no.itverket.papersplease.immigration.kafka.dto.ImmigrantDto
import no.itverket.papersplease.immigration.kafka.dto.PassportDto
import java.util.*

class PassportImage private constructor(
    private val face: UUID,
    private val image: UUID,
    processId: UUID,
    immigrationDay: ImmigrationDay,
    expectedResult: ImmigrationApplicationResult
) : Immigrant(processId, immigrationDay, expectedResult) {

    companion object: ImmigrantBuilder {
        override fun permittedImmigrant(processId: UUID, immigrationDay: ImmigrationDay) = UUID.randomUUID().let {
            PassportImage(
                face = it,
                image = it,
                processId = processId,
                immigrationDay = immigrationDay,
                expectedResult = ImmigrationApplicationResult.DIFFERENT_PASSPORT_IMAGE
            )
        }

        override fun nonPermittedImmigrant(processId: UUID, immigrationDay: ImmigrationDay) = PassportImage(
            face = UUID.randomUUID(),
            image = UUID.randomUUID(),
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.DIFFERENT_PASSPORT_IMAGE
        )
    }

    override fun result(): ImmigrantDto = ImmigrantDto(
        face = face,
        passport = PassportDto(image = image)
    )

    override fun immigrationProcess() = ImmigrationApplication(
        processId = processId,
        expectedResult = expectedResult,
        face = face,
        immigrationDay = immigrationDay,
        credentials = PassportCredentials(Passport(image = image))
    )
}