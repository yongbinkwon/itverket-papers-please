package no.itverket.papersplease

import no.itverket.papersplease.immigration.kafka.ImmigrantProducerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(ImmigrantProducerProperties::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}