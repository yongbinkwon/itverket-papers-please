package no.itverket.papersplease.immigration

import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrant.immigrationcontrol.PassportImage
import no.itverket.papersplease.immigration.immigrant.immigrationcontrol.MatchingVisaImage
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationRepository
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import no.itverket.papersplease.immigration.kafka.ImmigrantProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ImmigrationOffice(
    private val immigrantProducer: ImmigrantProducer,
    private val immigrationApplicationRepository: ImmigrationApplicationRepository
) {

    @Transactional
    fun queueImmigrant(
        expectedResult: ImmigrationApplicationResult,
        immigrationDay: ImmigrationDay
    ) {
        val processId = UUID.randomUUID()
        val immigrant = randomImmigrant(expectedResult, processId, immigrationDay)
        immigrationApplicationRepository.save(immigrant.immigrationProcess())
        immigrantProducer.publishImmigrant(immigrationDay, processId, immigrant.result())
    }

    private fun randomImmigrant(
        expectedResult: ImmigrationApplicationResult,
        processId: UUID,
        immigrationDay: ImmigrationDay
    ) = when(expectedResult) {
        ImmigrationApplicationResult.MATCHING_PASSPORT_IMAGE -> PassportImage(processId, immigrationDay)
        ImmigrationApplicationResult.MATCHING_VISA_IMAGE -> MatchingVisaImage(processId, immigrationDay)
        ImmigrationApplicationResult.RESIDENT -> PassportImage(processId, immigrationDay)
    }
}