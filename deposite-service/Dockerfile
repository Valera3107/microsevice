FROM openjdk:latest

ADD build/libs/deposite-service-1.0-SNAPSHOT.jar deposit.jar

ENTRYPOINT ["java", "-jar", "/deposit.jar"]

EXPOSE 9898