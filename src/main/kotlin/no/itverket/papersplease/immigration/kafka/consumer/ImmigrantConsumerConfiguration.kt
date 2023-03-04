package no.itverket.papersplease.immigration.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
@EnableKafka
internal class ImmigrantConsumerConfiguration {

    @Bean("immigrantContainerFactory")
    fun immigrantContainerFactory() =
        ConcurrentKafkaListenerContainerFactory<String, String>().apply {
            setConcurrency(1)
            consumerFactory = DefaultKafkaConsumerFactory(defaultConsumerConfigs())
            containerProperties.pollTimeout = Long.MAX_VALUE
        }

    private fun defaultConsumerConfigs() =
        mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "http://localhost:29092",
            ConsumerConfig.GROUP_ID_CONFIG to "immigrant-v1",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        )

}