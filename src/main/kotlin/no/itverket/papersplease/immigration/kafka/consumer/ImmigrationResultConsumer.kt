package no.itverket.papersplease.immigration.kafka.consumer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationRepository
import no.itverket.papersplease.immigration.kafka.consumer.dto.ImmigrationResultDto
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.*


@Service
internal class ImmigrationResultConsumer(
    private val immigrationApplicationRepository: ImmigrationApplicationRepository
) {
    companion object {
        private val objectMapper = jacksonObjectMapper()
    }

    @KafkaListener(
        topics = ["\${kafka.immigration-result.consumer.topic}"],
        containerFactory = "immigrationResultListenerContainerFactory"
    )
    fun receive(message: ConsumerRecord<String, String>) {
        val groupId = message.key()
        val result: ImmigrationResultDto = objectMapper.readValue(message.value())
        val expectedResult = immigrationApplicationRepository.findByProcessId(result.processId)
        expectedResult.difference(groupId, result)?.let { println(it) } ?: println("HEIHEI")
    }
}