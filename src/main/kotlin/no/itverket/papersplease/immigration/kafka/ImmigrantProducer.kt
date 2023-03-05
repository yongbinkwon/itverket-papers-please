package no.itverket.papersplease.immigration.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service


@Service
class ImmigrantProducer(
    private val immigrantProducerProperties: ImmigrantProducerProperties
) {

    private val producer = KafkaProducer<String, String>(
        immigrantProducerProperties.producerProperties
    )

    fun publishImmigrant(message: String) {
        producer.send(
            ProducerRecord(
                immigrantProducerProperties.topic,
                "hei",
                message
            )
        )
    }
}