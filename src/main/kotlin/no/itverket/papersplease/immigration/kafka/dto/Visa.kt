package no.itverket.papersplease.immigration.kafka.dto

data class Visa(
    val employerName: String,
    val employeeName: String?,
    val employeeId: String?,
    val image: String?
)