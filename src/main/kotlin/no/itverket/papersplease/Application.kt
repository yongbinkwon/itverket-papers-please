package no.itverket.papersplease

import no.itverket.papersplease.immigration.kafka.consumer.ImmigrationResultConsumerProperties
import no.itverket.papersplease.immigration.kafka.producer.ImmigrantProducerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(value = [ImmigrantProducerProperties::class, ImmigrationResultConsumerProperties::class])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}