FROM java:openjdk-8u111-jre

LABEL authors="allthatjava"

EXPOSE 8080

ADD build/libs/boot-example-web-app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]