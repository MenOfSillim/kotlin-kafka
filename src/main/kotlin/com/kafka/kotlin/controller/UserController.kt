package com.kafka.kotlin.controller

import com.kafka.kotlin.model.User
import com.kafka.kotlin.repository.UserRepository
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class UserController {
    @Autowired
    lateinit var userRepository: UserRepository

    @ApiOperation(value="User 정보 조회",notes="User에 대한 정보를 조회한다")
    @GetMapping("/user/{userId}")
    fun getUserInfo(@PathVariable(value = "userId") userId: String): Optional<User> {
        return userRepository.findById(userId)
    }
}