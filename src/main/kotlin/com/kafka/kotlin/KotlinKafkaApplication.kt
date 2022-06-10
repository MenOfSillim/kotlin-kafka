package com.kafka.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class KotlinKafkaApplication

fun main(args: Array<String>) {
    runApplication<KotlinKafkaApplication>(*args)
}
