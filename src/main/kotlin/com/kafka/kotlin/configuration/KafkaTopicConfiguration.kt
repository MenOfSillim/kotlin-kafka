package com.kafka.kotlin.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfiguration {

    // 토픽 빌드 및 신규토픽 생성
    @Bean
    fun newTopic() : KafkaAdmin.NewTopics {
        return KafkaAdmin.NewTopics(
                TopicBuilder.name("common").build()
        )
    }
}