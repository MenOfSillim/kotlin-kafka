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

# MongoDB 설정
```shell
# ================= mongoDB 설정 ===================
# mongo image pull 이후 -> image 구동
docker run --name kafka-mongo -v ~/data:/data/db -d -p 27017:27017 mongo

# mongo container connect
docker exec -it kafka-mongo bash

# mongo terminal connect
mongo

# kafka 사용, 없으면 생성 
use admin

# 현재 사용중인 DB 정보 조회
db

# DB 리스트 확인
show dbs

# 유저 생성
db.createUser( { user: "asdf", pwd: "asdf", roles: ["root"] }) # root 계정
db.createUser( { user: "asdf", pwd: "asdf", roles: ["dbOwner"] }) # 일반 계정

# 계정 삭제
db.dropUser("asdf")

# 계정 정보 조회
db.getUsers()

# user collection에 데이터 삽입
db.user.insert([{"userId":"1","nickname":"MenOfSillim","text":"Kafka Produce Test","createdDate":"2022-06-10T03:22:39.926650"}]);

# collection 값 확인
db.user.find()
```
## MongoDB 참고 사이트
https://velopert.com/457