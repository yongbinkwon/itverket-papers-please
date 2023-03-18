package no.itverket.papersplease.immigration.kafka.dto

data class ImmigrantDto(
    val visa: Visa?,
    val passport: Passport?,
    val face: String
) {
    init {
        require((visa==null) xor (passport==null))
    }
}