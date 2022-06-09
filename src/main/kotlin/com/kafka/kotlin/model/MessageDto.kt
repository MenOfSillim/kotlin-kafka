package com.kafka.kotlin.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class MessageDto(
    @ApiModelProperty(value = "닉네임", example = "MenOfSillim")
    @JsonProperty("nickname") val nickname: String?,

    @ApiModelProperty(value = "내용", example = "Kafka Produce Test")
    @JsonProperty("text") val text: String?,

    @ApiModelProperty(value = "시간", hidden = true)
    @JsonProperty("timestamp") var timestamp: String?
) {
    fun createTimestamp() {
        this.timestamp = LocalDateTime.now().toString()
    }
}