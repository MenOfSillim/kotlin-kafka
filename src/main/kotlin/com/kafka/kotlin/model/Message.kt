package com.kafka.kotlin.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class Message(
    @ApiModelProperty(value = "저자", example = "MenOfSillim")
    @JsonProperty("author") val author: String?,

    @ApiModelProperty(value = "내용", example = "Kafka Produce Test")
    @JsonProperty("text") val text: String?,

    @ApiModelProperty(value = "시간", hidden = true)
    @JsonProperty("timestamp") var timestamp: String?
) {
    fun createTimestamp() {
        this.timestamp = LocalDateTime.now().toString()
    }
}