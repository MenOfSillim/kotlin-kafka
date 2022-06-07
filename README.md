# Docker Build 방법

```shell
# /Users/david/Repository/MenOfSillim/kafka-kotlin
pwd

# 패키징
./gradlew clean build

# 공용 계정 menofdocker로 보내기 위해서 위와 같이 한다
docker build --platform=linux/amd64 -t menofdocker/kotlin-kafka .

# docker 외부 포트는 8010 , 내부 포트는 8080
docker run --platform=linux/amd64 -p 8010:8080 menofdocker/kotlin-kafka:latest /bin/bash

# ======================================
# 이미지를 원격 레포지토리에 푸시
docker push menofdocker/kotlin-kafka

# 도커 등 세팅 완료
# https://kafka.menofsillim.club/swagger-ui/index.html#/main-controller
```