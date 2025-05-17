FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /build

# Copia tudo necessário
COPY pom.xml pom.xml
COPY shared-entities shared-entities
COPY query-history query-history

# Instala o parent POM (raiz)
RUN mvn -N install -DskipTests

# Build shared-entities
RUN mvn -f shared-entities/pom.xml clean install -DskipTests

# Build o query-history
WORKDIR /build/query-history
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=builder /build/query-history/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
