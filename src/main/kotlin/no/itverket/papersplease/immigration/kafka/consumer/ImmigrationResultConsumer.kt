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
        private val rejectionMessages = listOf(
            "refused to let in a single mother of 3 kids >:)",
            "refused entry to their own parents :)",
            "kicked out the person who donated them their kidney :D",
            "denied their childhood love a chance at a new life on the other side of the border :>"
        )

        private val appovalMessages = listOf(
            "took a bribe to let a war criminal through",
            "approved entry for a chronic movie-talker",
            "let a pineapple-on-pizza-hater in"
        )
    }

    @KafkaListener(
        topics = ["\${kafka.immigration-result.consumer.topic}"],
        containerFactory = "immigrationResultListenerContainerFactory"
    )
    fun receive(message: ConsumerRecord<String, String>) {
        val groupId = message.key()
        val result: ImmigrationResultDto = objectMapper.readValue(message.value())
        val expectedResult = immigrationApplicationRepository.findByProcessId(result.processId)
        expectedResult.difference(groupId, result)?.let { println(it) } ?:
        println(correctResultString(groupId, result.permitted))
    }

    private fun correctResultString(groupId: String, permitted: Boolean) =
        "$groupId successfully ${correctResultType(permitted)}"

    private fun correctResultType(permitted: Boolean) =
        if (permitted) appovalMessages.random() else rejectionMessages.random()
}