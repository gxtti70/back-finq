# Paso 1: Compilar la aplicación usando Maven y Java 17 (puedes cambiar a eclipse-temurin:21-jdk si usas Java 21)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Paso 2: Crear la imagen ligera para correr la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto dinámico que Render asignará
EXPOSE 8080

# Comando para ejecutar la aplicación de Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
