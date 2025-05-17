FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml pom.xml
COPY shared-entities shared-entities
COPY appointment-scheduling appointment-scheduling

RUN mvn -N install -DskipTests

RUN mvn -f shared-entities/pom.xml clean install -DskipTests

WORKDIR /build/appointment-scheduling
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=builder /build/appointment-scheduling/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
