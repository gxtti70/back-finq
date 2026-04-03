# FinQ - Backend API ⚙️

<div align="center">
  <img src="https://img.shields.io/badge/status-en%20desarrollo-yellow?style=for-the-badge" alt="Estado">
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/hibernate-%2359666C.svg?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate">
</div>

---

## 📝 Descripción
Esta es la API REST de **FinQ**, encargada de procesar toda la lógica de negocio, persistencia de datos y gestión de transacciones financieras. Construida con **Spring Boot** y estructurada bajo una arquitectura de capas (Controller, Service, Repository, Entity).

## ✨ Funcionalidades del API
*   **CRUD de Transacciones:** Endpoint completo para crear, leer, actualizar y eliminar movimientos.
*   **Gestión de Categorías:** Clasificación automática de movimientos (Ingreso/Gasto) mediante IDs vinculados.
*   **Persistencia:** Integración robusta con **PostgreSQL** mediante Spring Data JPA.
*   **CORS Configurado:** Preparado para recibir peticiones desde el frontend en Angular.

## 🛠️ Stack Tecnológico
*   **Lenguaje:** Java 17 / 21
*   **Framework:** Spring Boot 3.x
*   **Base de Datos:** PostgreSQL
*   **ORM:** Hibernate
*   **Gestor de Dependencias:** Maven

## 🚧 Próximos Pasos
- [ ] Implementación de Spring Security con JWT.
- [ ] Validación de campos mediante @Valid.
- [ ] Documentación interactiva con Swagger/OpenAPI.
- [ ] Tests unitarios con JUnit y Mockito.

## 🚀 Cómo ejecutar
1.  Configura tu base de datos en `src/main/resources/application.properties`.
2.  Ejecuta el proyecto con Maven:
    ```bash
    ./mvnw spring-boot:run
    ```
---
Desarrollado por [Santiago Muñoz](https://github.com/gxtti70).
