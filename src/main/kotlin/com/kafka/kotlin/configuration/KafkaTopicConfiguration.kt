package com.kafka.kotlin.configuration

import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin


@Configuration
class KafkaTopicConfiguration {

    @Value("\${spring.kafka.bootstrap-servers}")
    lateinit var bootstrapServer: String

    @Bean
    fun kafkaAdmin(): KafkaAdmin? {
        val configs: MutableMap<String, Any> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        return KafkaAdmin(configs)
    }


    // 토픽 빌드 및 신규토픽 생성
    @Bean
    fun newTopic() : KafkaAdmin.NewTopics {
        return KafkaAdmin.NewTopics(
                TopicBuilder.name("common").build()
        )
    }
}