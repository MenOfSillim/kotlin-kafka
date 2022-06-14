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
docker run --name kafka-mongo -v /mnt/storage1/db-data/mongo:/data/db -d -p 27017:27017 --add-host=host.docker.internal:host-gateway  menofdocker/mongo

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

## 홈서버 설정
### MongoDB 설정
```shell

docker pull mongo
docker tag mongo menofdocker/mongo:latest
docker push menofdocker/mongo:latest

docker run --name kafka-mongo \
          -v /mnt/storage1/db-data/mongo:/data/db \
          -d -p 27017:27017 \
          --add-host=host.docker.internal:host-gateway \
          --restart unless-stopped menofdocker/mongo
docker exec -it kafka-mongo bash

mongo
use kafka
db.createUser({ user: "menofsillim", pwd: "sillim123!", roles: [ "dbAdmin" ] })
db.createCollection("user")
```

### Kafka 설치
```shell
# JDK 11 설치


# zookeeper download
wget https://dlcdn.apache.org/zookeeper/zookeeper-3.8.0/apache-zookeeper-3.8.0-bin.tar.gz
tar -xvf apache-zookeeper-3.8.0-bin.tar.gz

# kafka download
wget https://dlcdn.apache.org/kafka/3.2.0/kafka_2.13-3.2.0.tgz
tar -xvf kafka_2.13-3.2.0.tgz

# zookeeper 설정 세팅
cd conf
cp zoo_sample.cfg zoo.cfg

# ================= 실행 =================
# 1. foreground 모드
/home/david/kafka/apache-zookeeper-3.8.0-bin/bin/zkServer.sh start-foreground
/home/david/kafka/kafka_2.13-3.2.0/bin/kafka-server-start.sh /home/david/kafka/kafka_2.13-3.2.0/config/server.properties 

# 2. background 모드
/home/david/kafka/apache-zookeeper-3.8.0-bin/bin/zkServer.sh start
/home/david/kafka/kafka_2.13-3.2.0/bin/kafka-server-start.sh -daemon /home/david/kafka/kafka_2.13-3.2.0/config/server.properties

# ================= 중지 =================
# 2. background 모드
/home/david/kafka/apache-zookeeper-3.8.0-bin/bin/zkServer.sh stop
/home/david/kafka/kafka_2.13-3.2.0/bin/kafka-server-stop.sh -daemon

# 토픽 생성
bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092

# 토픽 확인
/home/david/kafka/kafka_2.13-3.2.0/bin/kafka-topics.sh --list --bootstrap-server localhost:9092

docker run -d --name kotlin-kafka -p 30010:20010 --add-host=host.docker.internal:host-gateway menofdocker/kotlin-kafka:latest | docker logs --tail 10 -f kotlin-kafka
```

https://twofootdog.tistory.com/89

- Application in Docker & Kafka Resolve Conflict
https://www.confluent.io/ko-kr/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/#adding-new-listener

```shell

docker run -d --name youtube -p30002:3000 --restart unless-stopped kyondoku/youtube
docker run --name kafka-mongo \
>           -v /mnt/storage1/db-data/mongo:/data/db \
>           -d -p 27017:27017 \
>           --add-host=host.docker.internal:host-gateway \
>           --restart unless-stopped menofdocker/mongo
```

```shell
# 컨테이너 작동 불가 이슈 해결
sudo systemctl stop apparmor
sudo systemctl disable apparmor
sudo apt remove --assume-yes --purge apparmor
# 리부팅 이후 도커 몇 개 설정 없음, docker pull로 없는 부분 다시 가져와야함

# zookeeper 자동 로딩 이슈 해결
sudo vi /etc/systemd/system/zookeeper.service
After=network.target

[Service]
Type=forking
User=david
Group=david
SyslogIdentifier=zookeeper-server
WorkingDirectory=/home/david/kafka/zookeeper/
Restart=always
RestartSec=0s
ExecStart=/home/david/kafka/zookeeper/bin/zkServer.sh start
ExecStop=/home/david/kafka/zookeeper/bin/zkServer.sh stop

[Install]
WantedBy=multi-user.target
~                          
sudo vi /etc/systemd/system/kafka.service
[Unit]
Description=kafka
Requires=network.target remote-fs.target zookeeper.service
After=network.target remote-fs.target zookeeper.service

[Service]
Type=forking
User=david
Group=david
SyslogIdentifier=kafka
WorkingDirectory=/home/david/kafka/kafka-broker/
Environment='KAFKA_HEAP_OPTS=-Xms500m -Xmx500m'
ExecStart=/home/david/kafka/kafka-broker/bin/kafka-server-start.sh -daemon /home/david/kafka/kafka-broker/config/server.properties
ExecStop=/home/david/kafka/kafka-broker/bin/kafka-server-stop.sh /home/david/kafka/kafka-broker/config/server.properties
Restart=on-abnormal

[Install]
WantedBy=multi-user.target 
~                                                                                                                                                                                                                 
~                                             
```