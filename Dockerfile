FROM openjdk:8-jdk-alpine

ARG JAR_FILE

COPY ${JAR_FILE} /opt/app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app.jar"]

EXPOSE 8080
