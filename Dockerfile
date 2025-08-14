# Étape de build
FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY .env .env
ENTRYPOINT ["java","-jar","/app.jar"]
