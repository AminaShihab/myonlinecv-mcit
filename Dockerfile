FROM azul/zulu-openjdk-alpine:11-jre
ARG JAR_FILE=target/*.jar
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]