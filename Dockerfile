#BUILD STAGE
FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#PACKAGE STAGE
FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]