package com.kafka.kotlin.controller

import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @ApiOperation(value="회원 목록",notes=" 회원전체 목록을 반환한다.")
    @GetMapping("/product/{id}")
    fun getById(@PathVariable(value = "id") id: Long): String {
        return "ID : $id"
    }

    @ApiOperation(value="회원 목록 리스트",notes=" 회원전체 목록을 반환한다.")
    @GetMapping("/product")
    fun getByIds(): Array<String> {
        return arrayOf("1","2","3")
    }
}