# Fase de construcción con Maven 3.9.9 y Java 21
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
# Descarga dependencias primero (cachea si no hay cambios en pom.xml)
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -Dmaven.test.skip=true

# Fase de ejecución (solo JRE)
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copia el JAR desde la fase de construcción
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]