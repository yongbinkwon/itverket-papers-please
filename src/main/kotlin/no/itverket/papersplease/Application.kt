package no.itverket.papersplease

import no.itverket.papersplease.immigration.kafka.ImmigrantProducerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ImmigrantProducerProperties::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}