package no.itverket.papersplease.immigration

import no.itverket.papersplease.immigration.kafka.ImmigrantProducer
import no.itverket.papersplease.immigration.kafka.ImmigrantProducerProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@Service
class ImmigrationOffice(
    private val immigrantProducer: ImmigrantProducer
) {

    fun queueImmigrant() {
        immigrantProducer.publishImmigrant("hei")
    }
}