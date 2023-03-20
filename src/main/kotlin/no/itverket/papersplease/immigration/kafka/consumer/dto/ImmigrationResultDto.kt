package no.itverket.papersplease.immigration.kafka.consumer.dto

import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import java.util.*

data class ImmigrationResultDto(
    val processId: UUID,
    val permitted: Boolean,
    val reason: ImmigrationApplicationResult
)