import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.kafka"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka") // Kafka
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // 기본 생성자 deserilize가 힘들어서 모듈 추가 : https://traeper.tistory.com/entry/Kotlin-jackson-module-kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect") // reflect 기능 : https://sabarada.tistory.com/190
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // Kotlin

    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA, mongo entity의 다양한 annotation
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb") // mongoDB

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Open-API : Swagger
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-kotlin
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.6")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.6")


    // prometheus
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // https://mvnrepository.com/artifact/io.micrometer/micrometer-registry-prometheus
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

