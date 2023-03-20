package no.itverket.papersplease.immigration.kafka.producer.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
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

    fun json(): String = objectMapper.writeValueAsString(this)
}