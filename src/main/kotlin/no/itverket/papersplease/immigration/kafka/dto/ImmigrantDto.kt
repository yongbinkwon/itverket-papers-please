package no.itverket.papersplease.immigration.kafka.dto

data class ImmigrantDto(
    val visa: Visa?,
    val passport: Passport?
) {
    init {
        require((visa==null) xor (passport==null))
    }
}