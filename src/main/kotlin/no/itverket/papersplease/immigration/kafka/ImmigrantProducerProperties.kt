package no.itverket.papersplease.immigration.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kafka.immigrant.producer")
class ImmigrantProducerProperties(
    clientId: String,
    brokerUrl: String,
    acksConfig: String,
    val topic: String
) {
   val producerProperties = mapOf(
        ProducerConfig.CLIENT_ID_CONFIG to clientId,
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to brokerUrl,
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ProducerConfig.ACKS_CONFIG to acksConfig
    )
}