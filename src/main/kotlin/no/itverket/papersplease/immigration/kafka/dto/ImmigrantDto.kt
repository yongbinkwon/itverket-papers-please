package no.itverket.papersplease.immigration.kafka.dto

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.*

data class ImmigrantDto(
    val visa: VisaDto? = null,
    val passport: PassportDto? = null,
    val face: UUID
) {
    init {
        require((visa==null) xor (passport==null))
    }

    companion object {
        private val objectMapper = jacksonObjectMapper()
    }

    fun json() = objectMapper.writeValueAsString(this)
}