FROM openjdk:latest

ADD build/libs/config-service-1.0-SNAPSHOT.jar config.jar

ENTRYPOINT ["java", "-jar", "/config.jar"]

EXPOSE 8001