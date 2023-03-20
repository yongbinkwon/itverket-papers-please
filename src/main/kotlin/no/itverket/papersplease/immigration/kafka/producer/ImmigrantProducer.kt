package no.itverket.papersplease.immigration.kafka.producer

import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.kafka.producer.dto.ImmigrantDto
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service
import java.util.*


@Service
class ImmigrantProducer(
    private val immigrantProducerProperties: ImmigrantProducerProperties
) {

    private val producer = KafkaProducer<String, String>(
        immigrantProducerProperties.producerProperties
    )

    fun publishImmigrant(immigrationDay: ImmigrationDay, processId: UUID, immigrantDto: ImmigrantDto) {
        producer.send(
            ProducerRecord(
                topicUrl(immigrationDay),
                processId.toString(),
                immigrantDto.json()
            )
        )
    }

    private fun topicUrl(immigrationDay: ImmigrationDay) =
        immigrantProducerProperties.topics[immigrationDay] ?: throw IllegalArgumentException("topic doesnt exist")
}