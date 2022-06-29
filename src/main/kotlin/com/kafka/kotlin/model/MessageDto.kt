package com.kafka.kotlin.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
//import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class MessageDto(
    @Schema(description = "닉네임", example = "MenOfSillim")
    @JsonProperty("nickname") val nickname: String?,

    @Schema(description = "닉네임", example = "Kafka Produce Test")
    @JsonProperty("text") val text: String?,

    @Schema(description = "시간", hidden = true)
    @JsonProperty("timestamp") var timestamp: String?
) {
    fun createTimestamp() {
        this.timestamp = LocalDateTime.now().toString()
    }
}