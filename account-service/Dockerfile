FROM openjdk:latest

ADD build/libs/account-service-1.0-SNAPSHOT.jar account.jar

ENTRYPOINT ["java", "-jar", "/account.jar"]

EXPOSE 8081