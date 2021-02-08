FROM openjdk:latest

ADD build/libs/gateway-1.0-SNAPSHOT.jar gateway.jar

ENTRYPOINT ["java", "-jar", "/gateway.jar"]

EXPOSE 8989