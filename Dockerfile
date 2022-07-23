FROM openjdk:17
ARG JAR_FILE=target/myweb*.jar
COPY ${JAR_FILE} app.jar
COPY ./src/main/ ./src/main/
COPY pom.xml ./pom.xml
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]