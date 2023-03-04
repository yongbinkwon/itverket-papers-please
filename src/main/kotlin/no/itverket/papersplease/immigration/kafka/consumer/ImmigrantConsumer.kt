package no.itverket.papersplease.immigration.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
internal class ImmigrantConsumer {
    @KafkaListener(
        topics = ["itverket-immigration"],
        containerFactory = "immigrantContainerFactory"
    )
    fun receive(message: ConsumerRecord<String, String>) {
        println("CONSOOOOMING")
        println(message.value())
    }
}