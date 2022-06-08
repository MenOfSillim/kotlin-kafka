package com.kafka.kotlin.controller

import com.kafka.kotlin.model.Message
import io.swagger.annotations.ApiOperation
import org.springframework.kafka.core.KafkaProducerException
import org.springframework.kafka.core.KafkaSendCallback
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.web.bind.annotation.*


@RestController
class ProduceController(private var kafkaTemplate: KafkaTemplate<String, Message>) {

    @ApiOperation(value="Kafka 메세지 발행",notes="해당 Topic에 필요한 Message를 발행한다")
    @PostMapping("/produce/{topic}")
    @Throws(InterruptedException::class)
    fun produceCommonMessage(@PathVariable(value = "topic") topic: String, @RequestBody message: Message): Message {
        message.createTimestamp()
        val listenableFuture = kafkaTemplate.send(topic, message)
        listenableFuture.addCallback(listenableFutureCallback(message))
        return message
    }

    fun listenableFutureCallback(message: Message) =
        object: KafkaSendCallback<String, Message> {
            override fun onSuccess(result: SendResult<String, Message>?) {
                println(
                    "Send Message = [ $message ] with offset=[ ${result!!.recordMetadata.offset()} ]"
                )
            }

            override fun onFailure(ex: KafkaProducerException) {
                println(
                    "Message 전달 오류 [ $message ] due to: ${ex.getFailedProducerRecord<String, String>()}"
                )
            }
        }
}