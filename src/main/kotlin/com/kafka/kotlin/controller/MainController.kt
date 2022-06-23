package com.kafka.kotlin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "MainController")
@RestController
class MainController {

    @Operation(summary = "PathVariable을 테스트하는 API")
    @GetMapping("/product/{id}")
    fun getById(@PathVariable(value = "id") id: Long): String {
        return "ID : $id"
    }

    @Operation(summary = "Get Request를 테스트하는 API")
    @GetMapping("/product")
    fun getByIds(): Array<String> {
        return arrayOf("1","2","3")
    }
}