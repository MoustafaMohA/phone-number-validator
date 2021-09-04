FROM maven:3-openjdk-11 as buildJar
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn test

FROM openjdk:11
COPY --from=buildJar /app/target/*.jar application.jar
COPY --from=buildJar /app/sample.db  sample.db
ENTRYPOINT ["java","-jar","/application.jar"]