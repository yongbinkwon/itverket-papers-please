package no.itverket.papersplease.immigration.kafka.dto

import java.util.*

data class PassportDto(
    val nationality: String? = null,
    val socialSecurityNumber: UUID? = null,
    val image: UUID
)