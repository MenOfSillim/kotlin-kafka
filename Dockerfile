FROM adoptopenjdk/openjdk11:ubi
EXPOSE 8010
ADD build/libs/*.jar kotlin-kafka.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "kotlin-kafka.jar"]