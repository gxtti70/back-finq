# Paso 1: Compilar la aplicación usando Maven y Java 17
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .

# Nos movemos a la subcarpeta donde realmente está el proyecto de Spring Boot
WORKDIR /app/finq-api

# Ejecutamos la compilación (aquí sí encontrará el pom.xml y el mvnw)
RUN ./mvnw clean package -DskipTests

# Paso 2: Crear la imagen ligera para correr la aplicación usando Temurin 17
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Nos traemos el .jar generado desde la subcarpeta target de finq-api
COPY --from=build /app/finq-api/target/*.jar app.jar

# Exponer el puerto por defecto
EXPOSE 8080

# Comando para ejecutar la aplicación de Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
