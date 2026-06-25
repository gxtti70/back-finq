# Paso 1: Compilar la aplicación usando Maven y Java 17
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Paso 2: Crear la imagen ligera para correr la aplicación usando Temurin 17
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto por defecto (Render mapeará esto con la variable PORT)
EXPOSE 8080

# Comando para ejecutar la aplicación de Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
