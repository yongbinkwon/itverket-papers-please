package no.itverket.papersplease.immigration.kafka.dto

import java.util.*

data class VisaDto(
    val employerName: String,
    val employeeName: String?,
    val employeeId: UUID?,
    val image: UUID?
)