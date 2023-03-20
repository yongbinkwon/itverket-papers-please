package no.itverket.papersplease.immigration.kafka.dto

import java.util.*

data class PassportDto(
    val nationality: String,
    val socialSecurityNumber: UUID,
    val image: UUID
)