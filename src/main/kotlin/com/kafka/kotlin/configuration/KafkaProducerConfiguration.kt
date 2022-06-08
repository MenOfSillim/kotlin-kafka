package com.kafka.kotlin.configuration

import com.kafka.kotlin.model.Message
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer
import java.io.Serializable

@EnableKafka
@Configuration // @Configuration 어노테이션을 사용하여 설정파일임을 명시하고 bean 등록이 가능하게 한다
class KafkaProducerConfig {

    @Value("\${spring.kafka.producer.bootstrap-servers}")
    lateinit var bootstrapServer: String

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Message> {
        val factory = DefaultKafkaProducerFactory<String, Message>(producerConfigs())
        return KafkaTemplate(factory)
    }

    @Bean
    fun producerConfigs(): Map<String, Serializable> =
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServer,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java, // 메시지 key에 대한 직렬화 설정
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java // 메시지 value에 대한 직렬화 설정
        )
}