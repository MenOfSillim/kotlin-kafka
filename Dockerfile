FROM adoptopenjdk/openjdk11:ubi
EXPOSE 8010
ADD build/libs/*.jar service2.jar
ENTRYPOINT ["java", "-jar", "service2.jar"]