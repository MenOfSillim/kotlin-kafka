package com.kafka.kotlin.service

interface ConsumerService {
    fun kafkaListenerService(message: String)
}