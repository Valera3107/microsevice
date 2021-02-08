FROM openjdk:latest

ADD build/libs/bill-service-1.0-SNAPSHOT.jar bill.jar

ENTRYPOINT ["java", "-jar", "/bill.jar"]

EXPOSE 8082