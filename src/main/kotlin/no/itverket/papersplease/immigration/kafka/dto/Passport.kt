package no.itverket.papersplease.immigration.kafka.dto

data class Passport(
    val nationality: String,
    val socialSecurityNumber: String?,
    val image: String?
)