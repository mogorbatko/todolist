FROM maven:3.8.4-openjdk-11 AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:11

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/todolist-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "todolist-0.0.1-SNAPSHOT.jar"]