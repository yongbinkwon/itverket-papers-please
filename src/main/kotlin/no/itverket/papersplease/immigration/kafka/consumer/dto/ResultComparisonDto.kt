package no.itverket.papersplease.immigration.kafka.consumer.dto

import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import java.util.*

data class ResultComparisonDto(
    val groupId: String,
    val processId: UUID,
    val expected: ImmigrationApplicationResult,
    val actually: ImmigrationApplicationResult
)