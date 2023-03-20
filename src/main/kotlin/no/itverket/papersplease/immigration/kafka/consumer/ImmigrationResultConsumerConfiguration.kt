package no.itverket.papersplease.immigration.kafka.consumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
@EnableKafka
internal class ImmigrationResultConsumerConfiguration {

    @Bean("immigrationResultListenerContainerFactory")
    fun immigrantListenerContainerFactory(
        properties: ImmigrationResultConsumerProperties
    ) = ConcurrentKafkaListenerContainerFactory<String, String>().apply {
        setConcurrency(1)
        consumerFactory = DefaultKafkaConsumerFactory(properties.config)
        containerProperties.pollTimeout = Long.MAX_VALUE
    }

}