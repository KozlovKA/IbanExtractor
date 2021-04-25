FROM openjdk:8-jdk-alpine
COPY ${JAR_FILE} IbanExtractor-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/IbanExtractor-0.0.1-SNAPSHOT.jar"]