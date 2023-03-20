package no.itverket.papersplease.immigration.kafka.producer.dto

import java.util.*

data class VisaDto(
    val employer: String,
    val employeeId: UUID,
    val image: UUID
)