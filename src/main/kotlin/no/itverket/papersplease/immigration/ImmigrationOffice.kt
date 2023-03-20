package no.itverket.papersplease.immigration

import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrant.immigranttype.passport.Foreigner
import no.itverket.papersplease.immigration.immigrant.immigranttype.passport.PassportImmigrant
import no.itverket.papersplease.immigration.immigrant.immigranttype.passport.Resident
import no.itverket.papersplease.immigration.immigrant.immigranttype.visa.VisaImmigrant
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationRepository
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult.*
import no.itverket.papersplease.immigration.kafka.ImmigrantProducer
import no.itverket.papersplease.immigration.registry.*
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ImmigrationOffice(
    private val immigrantProducer: ImmigrantProducer,
    private val immigrationApplicationRepository: ImmigrationApplicationRepository,
    private val companyRegistryRepository: CompanyRegistryRepository,
    private val foreignerRegistryRepository: ForeignerRegistryRepository,
    private val nationalRegistryRepository: NationalRegistryRepository
) {
    private val dayZero = listOf(RESIDENT, VISA_IMMIGRANT, INVALID_PASSPORT_IMAGE, INVALID_VISA_IMAGE)
    private val dayOne = dayZero + listOf(INVALID_EMPLOYER, INVALID_FOREIGN_NATION)
    private val dayTwo = dayOne + listOf(FOREIGNER)
    private val dayThree = dayTwo + listOf(UNREGISTERED_RESIDENT, UNREGISTERED_FOREIGNER, UNREGISTERED_VISA_IMMIGRANT)

    private val validStatesByDay = mapOf(
        ImmigrationDay.ZERO to dayZero,
        ImmigrationDay.ONE to dayOne,
        ImmigrationDay.TWO to dayTwo,
        ImmigrationDay.THREE to dayThree
    )

    @Transactional
    private fun queueImmigrant(
        expectedResult: ImmigrationApplicationResult,
        immigrationDay: ImmigrationDay
    ) {
        val processId = UUID.randomUUID()
        val immigrant = randomImmigrant(expectedResult, processId, immigrationDay)
        immigrationApplicationRepository.save(immigrant.immigrationApplication())
        when(val registryEntry = immigrant.registryEntry()) {
            is NationalRegistry -> nationalRegistryRepository.save(registryEntry)
            is CompanyRegistry -> companyRegistryRepository.save(registryEntry)
            is ForeignerRegistry -> foreignerRegistryRepository.save(registryEntry)
        }
        immigrantProducer.publishImmigrant(immigrationDay, processId, immigrant.immigrantDto())
    }

    @Scheduled(initialDelay = 5*1000, fixedDelay = 5*1000)
    fun scheduleImmigration() {
        queueImmigrant(
            expectedResult = validStatesByDay[ImmigrationDay.ZERO]?.random() ?: throw Exception(),
            immigrationDay = ImmigrationDay.ZERO
        )

        /*
        queueImmigrant(
            expectedResult = validStatesByDay[ImmigrationDay.ONE]?.random() ?: throw Exception(),
            immigrationDay = ImmigrationDay.ONE
        )

        queueImmigrant(
            expectedResult = validStatesByDay[ImmigrationDay.TWO]?.random() ?: throw Exception(),
            immigrationDay = ImmigrationDay.TWO
        )

        queueImmigrant(
            expectedResult = validStatesByDay[ImmigrationDay.THREE]?.random() ?: throw Exception(),
            immigrationDay = ImmigrationDay.THREE
        )
         */
    }

    private fun randomImmigrant(
        expectedResult: ImmigrationApplicationResult,
        processId: UUID,
        immigrationDay: ImmigrationDay
    ) = when(expectedResult) {
        RESIDENT -> Resident.resident(processId, immigrationDay)
        UNREGISTERED_RESIDENT -> Resident.unregisteredResident(processId, immigrationDay)
        FOREIGNER -> Foreigner.validForeigner(processId, immigrationDay)
        UNREGISTERED_FOREIGNER -> Foreigner.unregisteredForeigner(processId, immigrationDay)
        INVALID_FOREIGN_NATION -> Foreigner.invalidNationality(processId, immigrationDay)
        INVALID_PASSPORT_IMAGE -> PassportImmigrant.invalidImage(processId, immigrationDay)
        VISA_IMMIGRANT -> VisaImmigrant.visaImmigrant(processId, immigrationDay)
        UNREGISTERED_VISA_IMMIGRANT -> VisaImmigrant.unregisteredVisaImmigrant(processId, immigrationDay)
        INVALID_EMPLOYER -> VisaImmigrant.invalidEmployer(processId, immigrationDay)
        INVALID_VISA_IMAGE -> VisaImmigrant.invalidImage(processId, immigrationDay)
    }
}