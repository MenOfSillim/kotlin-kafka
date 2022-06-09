package com.kafka.kotlin.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


@Document(collation = "user")
class User private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: String? = null,
    var nickname: String? = null,
    var text: String? = null,
){
    data class Builder(
        var userId: String? = null,
        var nickname: String? = null,
        var text: String? = null,
        var createdDate: LocalDateTime? = LocalDateTime.now()) {

        fun userId(userId: String) = apply { this.userId = userId }
        fun nickname(nickname: String) = apply { this.nickname = nickname }
        fun text(text: String) = apply { this.text = text }
        fun createdDate(createdDate: LocalDateTime) = apply { this.createdDate = createdDate }
        fun build() = User(userId, nickname, text)
    }
}

//{"userId":"1","nickname":"MenOfSillim","text":"Kafka Produce Test","createdDate":"2022-06-10T03:22:39.926650"}