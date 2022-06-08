package com.kafka.kotlin.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ConsumerServiceImpl : ConsumerService {

    @KafkaListener(id = "common-listener-id", topics = ["common"])
    override fun kafkaListenerService(message: String) {
        println(message)
    }
}