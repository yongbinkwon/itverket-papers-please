package no.itverket.papersplease.immigration

import no.itverket.papersplease.immigration.kafka.ImmigrantProducer
import org.springframework.stereotype.Service

@Service
class ImmigrationOffice(
    private val immigrantProducer: ImmigrantProducer
) {

    fun queueImmigrant() {
        immigrantProducer.publishImmigrant("hei")
    }
}