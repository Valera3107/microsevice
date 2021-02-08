FROM openjdk:latest

ADD build/libs/notification-service-1.0-SNAPSHOT.jar notification.jar

ENTRYPOINT ["java", "-jar", "/notification.jar"]

EXPOSE 9999