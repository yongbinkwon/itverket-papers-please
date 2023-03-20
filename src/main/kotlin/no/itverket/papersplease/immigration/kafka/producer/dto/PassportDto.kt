package no.itverket.papersplease.immigration.kafka.producer.dto

import java.util.*

data class PassportDto(
    val nationality: String,
    val socialSecurityNumber: UUID,
    val image: UUID
)