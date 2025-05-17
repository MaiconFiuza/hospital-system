FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml pom.xml
COPY shared-entities shared-entities
COPY order-notification order-notification

RUN mvn -N install -DskipTests

RUN mvn -f shared-entities/pom.xml clean install -DskipTests

WORKDIR /build/order-notification
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=builder /build/order-notification/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
