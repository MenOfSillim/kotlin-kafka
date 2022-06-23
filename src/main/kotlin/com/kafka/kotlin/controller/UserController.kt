package com.kafka.kotlin.controller

import com.kafka.kotlin.model.User
import com.kafka.kotlin.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
//import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@Tag(name = "UserController")
@RestController
class UserController {
    @Autowired
    lateinit var userRepository: UserRepository

    @Operation(summary = "Producer로 발행한 Data를 조회")
    @GetMapping("/user/{userId}")
    fun getUserInfo(@PathVariable(value = "userId") userId: String): Optional<User> {
        return userRepository.findById(userId)
    }
}