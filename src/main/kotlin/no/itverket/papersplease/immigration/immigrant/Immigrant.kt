package no.itverket.papersplease.immigration.immigrant

import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplication
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import no.itverket.papersplease.immigration.kafka.producer.dto.ImmigrantDto
import no.itverket.papersplease.immigration.registry.RegistryEntry
import java.util.*

abstract class Immigrant(
    protected val face: UUID,
    protected val image: UUID,
    protected val processId: UUID,
    protected val immigrationDay: ImmigrationDay,
    protected val expectedResult: ImmigrationApplicationResult,
) {
    abstract fun immigrantDto(): ImmigrantDto

    abstract fun immigrationApplication(): ImmigrationApplication

    open fun registryEntry(): RegistryEntry? = null
}