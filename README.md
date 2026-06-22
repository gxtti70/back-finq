# 💰 FinQ - Backend API | Gestión Financiera Personal

<div align="center">
  <img src="https://img.shields.io/badge/status-TERMINADO%20%7C%20Listo%20para%20desplegar-brightgreen?style=for-the-badge" alt="Estado">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot 3.x">
  <img src="https://img.shields.io/badge/Java-17%2F21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17/21">
  <img src="https://img.shields.io/badge/PostgreSQL-15%2B-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Hibernate-6.x-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate">
</div>

**FinQ Backend** es una API REST robuusta y escalable construida con **Spring Boot 3.x** que gestiona toda la lógica de negocio, persistencia de datos y operaciones financieras. Diseñada siguiendo principios SOLID, ofrece endpoints optimizados para manejar transacciones, categorías y reportes financieros.

> ✅ **Estado del Proyecto:** El backend está **100% TERMINADO Y FUNCIONAL**. Solo falta el despliegue en producción.

---

## ✨ Características Principales

* 💳 **CRUD de Transacciones:** Endpoints completos para crear, leer, actualizar y eliminar movimientos financieros.
* 🏷️ **Gestión de Categorías:** Clasificación automática de movimientos (Ingreso/Gasto) con validación de integridad referencial.
* 💾 **Persistencia Robusta:** Integración con **PostgreSQL** mediante Spring Data JPA y Hibernate ORM.
* 🔄 **CORS Configurado:** Preparado para recibir peticiones desde el frontend Angular sin restricciones.
* ✔️ **Validación de Datos:** Anotaciones @Valid para garantizar integridad de datos en request/response.
* 🛡️ **Manejo de Excepciones:** Respuestas HTTP estructuradas con códigos de error apropiados.
* 📊 **Estadísticas Financieras:** Endpoints para cálculos de saldo, ingresos totales y gastos totales.

---

## 🛠️ Stack Tecnológico

**Backend:**
* **Lenguaje:** Java 17 / 21
* **Framework:** Spring Boot 3.x
* **Web:** Spring Web MVC
* **Data:** Spring Data JPA
* **ORM:** Hibernate 6.x
* **Base de Datos:** PostgreSQL 15+
* **Gestor de Dependencias:** Maven
* **Construcción:** Maven 3.x+

**Infraestructura:**
* **API REST:** JSON
* **CORS:** Configurado para desarrollo y producción
* **Despliegue:** Railway / Heroku / AWS

---

## 🎯 Funcionalidades Implementadas

### ✅ Transacciones
- [x] CREATE: Crear nuevas transacciones con validación
- [x] READ: Obtener todas las transacciones o una específica por ID
- [x] UPDATE: Actualizar datos de transacciones existentes
- [x] DELETE: Eliminar transacciones
- [x] Filtrado por categoría, tipo (ingreso/gasto) y rango de fechas
- [x] Cálculo automático de balances

### ✅ Categorías
- [x] CRUD completo de categorías
- [x] Validación de duplicados
- [x] Relación bidireccional con transacciones
- [x] Eliminación en cascada

### ✅ Estadísticas y Reportes
- [x] Cálculo de saldo total
- [x] Suma de ingresos
- [x] Suma de gastos
- [x] Resumen por categoría

### ✅ Arquitectura
- [x] Estructura MVC (Model-View-Controller)
- [x] Capas de Service y Repository
- [x] Validación con @Valid y @Validated
- [x] Manejo global de excepciones
- [x] Respuestas API estructuradas

---

## 📂 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/finq/
│   │   ├── controller/          # Endpoints REST
│   │   │   ├── TransactionController.java
│   │   │   └── CategoryController.java
│   │   ├── service/             # Lógica de negocio
│   │   │   ├── TransactionService.java
│   │   │   └── CategoryService.java
│   │   ├── repository/          # Acceso a datos (JPA)
│   │   │   ├── TransactionRepository.java
│   │   │   └── CategoryRepository.java
│   │   ├── model/               # Entidades JPA
│   │   │   ├── Transaction.java
│   │   │   └── Category.java
│   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── TransactionDTO.java
│   │   │   └── CategoryDTO.java
│   │   ├── exception/           # Excepciones personalizadas
│   │   │   └── ResourceNotFoundException.java
│   │   └── Application.java     # Clase principal
│   └── resources/
│       ├── application.properties
│       └── data.sql             # Datos iniciales
└── test/
    └── java/                    # Tests unitarios
```

---

## 🚀 Instalación y Uso Local

### Requisitos Previos
- **Java 17+** o **Java 21**
- **Maven 3.8+**
- **PostgreSQL 15+**
- **Git**

### Pasos de Instalación

1. **Clona el repositorio:**
```bash
git clone https://github.com/gxtti70/back-finq.git
cd back-finq
```

2. **Configura la base de datos:**
   Crea una base de datos PostgreSQL:
```sql
CREATE DATABASE finq_db;
```

3. **Configura las variables de entorno:**
   Crea un archivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/finq_db
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=false

server.port=8080

spring.web.cors.allowed-origins=http://localhost:4200,http://localhost:3000
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.web.cors.allowed-headers=*
```

4. **Instala las dependencias y ejecuta:**
```bash
mvn clean install
mvn spring-boot:run
```

5. **La API estará disponible en:**
   - http://localhost:8080
   - Endpoints disponibles: `/api/transactions`, `/api/categories`

### Build para Producción
```bash
mvn clean package -DskipTests
java -jar target/back-finq-*.jar
```

---

## 📡 Endpoints Principales

### Transacciones
```
GET    /api/transactions              # Obtener todas las transacciones
GET    /api/transactions/{id}         # Obtener una transacción por ID
POST   /api/transactions              # Crear nueva transacción
PUT    /api/transactions/{id}         # Actualizar transacción
DELETE /api/transactions/{id}         # Eliminar transacción
GET    /api/transactions/stats        # Obtener estadísticas
```

### Categorías
```
GET    /api/categories                # Obtener todas las categorías
GET    /api/categories/{id}           # Obtener una categoría por ID
POST   /api/categories                # Crear nueva categoría
PUT    /api/categories/{id}           # Actualizar categoría
DELETE /api/categories/{id}           # Eliminar categoría
```

---

## 🎯 Checklist de Completitud

- [x] Entidades JPA (Transaction, Category)
- [x] Repositorios Spring Data
- [x] Servicios con lógica de negocio
- [x] Controladores REST
- [x] DTOs para request/response
- [x] Validación de datos
- [x] Manejo de excepciones
- [x] CORS configurado
- [x] Relaciones entre entidades
- [x] Operaciones CRUD funcionales
- [x] Estadísticas y reportes
- [x] **Backend completamente terminado**
- [ ] 🚀 **Despliegue en Railway/Heroku** (PRÓXIMO PASO)

---

## 🚀 Próximo Paso: Despliegue

El backend está listo para ser desplegado en **Railway**, **Heroku** o **AWS**. Solo necesita:

1. Conectar el repositorio a la plataforma de despliegue
2. Configurar las variables de entorno (DATABASE_URL, etc.)
3. Ejecutar el build automático
4. ¡Listo! La API estará disponible en producción

---

## 🔗 Enlaces Relacionados

* **Frontend Angular:** [front-finq](https://github.com/gxtti70/front-finq)
* **Documentación Spring Boot:** https://spring.io/projects/spring-boot
* **Documentación Spring Data JPA:** https://spring.io/projects/spring-data-jpa
* **PostgreSQL:** https://www.postgresql.org/

---

## 📄 Licencia

Este proyecto está bajo licencia **MIT**. Siéntete libre de usar, modificar y distribuir este código.

---

## 👤 Autor

**Santiago Muñoz** - Full Stack Developer Junior

* GitHub: [@gxtti70](https://github.com/gxtti70)
* Portfolio: [porta-front-ten.vercel.app](https://porta-front-ten.vercel.app)

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Si deseas mejorar este proyecto:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

<div align="center">
  <p>⭐ Si te gusta este proyecto, no olvides darle una estrella</p>
  <p>Hecho con ❤️ por Santiago Muñoz</p>
</div>
