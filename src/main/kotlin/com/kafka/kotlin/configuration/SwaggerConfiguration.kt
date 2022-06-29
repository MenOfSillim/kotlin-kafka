package com.kafka.kotlin.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfiguration {

    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
                             .group("v1")
                             .pathsToMatch("/**")
                             .build()
    }

    @Bean
    fun openAPI(): OpenAPI? {
        return OpenAPI().info(Info()
                            .title("Proxy-Kafka")
                            .description(" Proxy-Kafka Page")
                            .version("v1.0.1")
                            .contact(Contact()
                                .name("MenOfSillim")
                                .email("zkffhtm6523@gmail.com")
                                .url("https://menofsillim.club")))
    }
}

