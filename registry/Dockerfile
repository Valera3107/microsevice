FROM openjdk:latest

ADD build/libs/registry-1.0-SNAPSHOT.jar registry.jar

ENTRYPOINT ["java", "-jar", "/registry.jar"]

EXPOSE 8761