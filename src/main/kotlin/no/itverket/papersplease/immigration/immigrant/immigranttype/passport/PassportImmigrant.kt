package no.itverket.papersplease.immigration.immigrant.immigranttype.passport

import no.itverket.papersplease.immigration.immigrant.Immigrant
import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplication
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import no.itverket.papersplease.immigration.immigrationapplication.credentials.passport.Passport
import no.itverket.papersplease.immigration.immigrationapplication.credentials.passport.PassportCredentials
import no.itverket.papersplease.immigration.kafka.dto.ImmigrantDto
import no.itverket.papersplease.immigration.kafka.dto.PassportDto
import java.util.*

open class PassportImmigrant(
    protected val nationality: String,
    protected val ssn: UUID,
    face: UUID,
    image: UUID,
    processId: UUID,
    immigrationDay: ImmigrationDay,
    expectedResult: ImmigrationApplicationResult,
) : Immigrant(face, image, processId, immigrationDay, expectedResult) {

    companion object {
        fun invalidImage(processId: UUID, immigrationDay: ImmigrationDay) = PassportImmigrant(
            nationality = "hei",
            ssn = UUID.randomUUID(),
            face = UUID.randomUUID(),
            image = UUID.randomUUID(),
            processId = processId,
            immigrationDay = immigrationDay,
            expectedResult = ImmigrationApplicationResult.INVALID_PASSPORT_IMAGE
        )
    }

    override fun immigrantDto(): ImmigrantDto = ImmigrantDto(
        face = face,
        passport = PassportDto(nationality = nationality, socialSecurityNumber = ssn, image = image)
    )

    override fun immigrationApplication() = ImmigrationApplication(
        processId = processId,
        expectedResult = expectedResult,
        face = face,
        immigrationDay = immigrationDay,
        credentials = PassportCredentials(passport())
    )

    private fun passport() = Passport(nationality = nationality, ssn = ssn, image = image)
}