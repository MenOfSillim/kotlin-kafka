package com.kafka.kotlin.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
@EnableWebMvc
class SwaggerConfiguration {

    @Value("\${spring.exposedHost}")
    lateinit var exposedHost: String

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .host(exposedHost)
        .apiInfo(getApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.kafka.kotlin.controller"))
//        .paths(PathSelectors.ant("/product/**"))
        .build()


    private fun getApiInfo(): ApiInfo {
        val contact = Contact("MenOfSillim", "https://menofsillim.club", "zkffhtm6523@gmail.com")
        return ApiInfoBuilder()
            .title("Api Title")
            .description("Api Definition")
            .version("1.0.0")
            .contact(contact)
            .build()
    }
}