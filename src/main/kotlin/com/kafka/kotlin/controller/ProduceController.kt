package com.kafka.kotlin.controller

import com.kafka.kotlin.model.MessageDto
import com.kafka.kotlin.model.User
import com.kafka.kotlin.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaProducerException
import org.springframework.kafka.core.KafkaSendCallback
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*


@Tag(name = "ProductController")
@RestController
class ProduceController(private var kafkaTemplate: KafkaTemplate<String, MessageDto>) {

    @Autowired
    lateinit var userRepository: UserRepository

    @Operation(summary = "Kafka 메세지 발행", description = "해당 Topic에 필요한 Message를 발행한다[common]")
    @PostMapping("/produce/{topic}")
    @Throws(InterruptedException::class)
    fun produceCommonMessage(@PathVariable(value = "topic") topic: String, @RequestBody messageDto: MessageDto): User {
        messageDto.createTimestamp()
        kafkaTemplate.send(topic, messageDto).addCallback(listenableFutureCallback(messageDto))
        return userRepository.insert(User.Builder()
                                  .userId(UUID.randomUUID().toString())
                                  .nickname(messageDto.nickname.toString())
                                  .text(messageDto.text.toString())
                                  .createdDate(LocalDateTime.parse(messageDto.timestamp))
                                  .build())
    }

    fun listenableFutureCallback(messageDto: MessageDto) =
        object: KafkaSendCallback<String, MessageDto> {
            override fun onSuccess(result: SendResult<String, MessageDto>?) {
                println(
                    "Message Success = [ $messageDto ] with offset=[ ${result!!.recordMetadata.offset()} ]"
                )
            }

            override fun onFailure(ex: KafkaProducerException) {
                println(
                    "Message Failure [ $messageDto ] due to: ${ex.getFailedProducerRecord<String, String>()}"
                )
            }
        }
}