# JAVA0078_EVA_M6_ProductoCRUD

Este proyecto implementa un **CRUD de productos** disponible vía **API
REST**, protegido con **JWT**, utilizando **Spring Boot 3**, **Spring
Security**, **JPA**, **MySQL**, y vistas de registro con **Thymeleaf** y
**Bootstrap**.

---

## Tecnologías utilizadas

-   Java 21\
-   Spring Boot 3.5.7\
-   Spring Web\
-   Spring Data JPA\
-   Spring Security (JWT)\
-   JJWT 0.11.5\
-   MySQL 8\
-   Thymeleaf\
-   Bootstrap 5\
-   Maven

---

## Estructura del proyecto

    JAVA0078_EVA_M6_ProductoCRUD
    │── src/main/java/cl/web
    │   ├── auth/
    │   │   └── AuthController.java
    │   ├── controllers/
    │   │   ├── ProductoController.java
    │   │   └── WebController.java
    │   ├── dto/
    │   │   ├── ProductoDTO.java
    │   │   └── UsuarioDTO.java
    │   ├── models/
    │   │   ├── Producto.java
    │   │   └── Usuario.java
    │   ├── repositories/
    │   │   ├── ProductoRepository.java
    │   │   └── UsuarioRepository.java
    │   ├── security/
    │   │   ├── JwtFiltroAutenticacion.java
    │   │   ├── JwtUtil.java
    │   │   └── SecurityConfig.java
    │   └── services/
    │       ├── ProductoService.java
    │       ├── ProductoServiceImpl.java
    │       ├── UsuarioService.java
    │       └── UsuarioServiceImpl.java
    │
    │── src/main/resources/
    │   ├── application.properties
    │   └── templates/register.html
    │
    │── inserts.sql
    │── pom.xml
    │── README.md
    └── Request api.txt

---

## Autenticación JWT

### 1 Registro de usuarios

Ruta abierta:

    POST /registro

Formulario HTML renderizado con **Thymeleaf**.

### 2 Login para obtener token JWT

Ruta abierta:

    POST /auth/login

Ejemplo JSON:

``` json
{
  "username": "admin",
  "password": "123456"
}
```

Respuesta:

``` json
{ "token": "xxxxx.yyyyy.zzzzz" }
```

---

## Endpoints del CRUD (protegidos con JWT)

| Método | Endpoint                 | Descripción               |
|--------|---------------------------|----------------------------|
| GET    | `/api/v1/productos`      | Lista todos los productos |
| GET    | `/api/v1/productos/{id}` | Obtiene producto por ID   |
| POST   | `/api/v1/productos`      | Crea un nuevo producto    |
| PUT    | `/api/v1/productos/{id}` | Actualiza un producto     |
| DELETE | `/api/v1/productos/{id}` | Elimina un producto       |

---

## Configuración base de datos (MySQL)

En `application.properties`:

    spring.datasource.url=jdbc:mysql://localhost:3306/java0078_m6
    spring.datasource.username=root
    spring.datasource.password=Admin1234.

Hibernate genera la tabla automáticamente:

    spring.jpa.hibernate.ddl-auto=update

---

## Cómo ejecutar el proyecto

1.  Clonar repositorio\
2.  Configurar base de datos MySQL\
3.  Actualizar credenciales en `application.properties`\
4.  Ejecutar con Maven o desde STS:

```
    mvn spring-boot:run
```
---

## Pruebas

1. **Registrar un usuario en la aplicación web**  
   Accede al formulario de registro:  
   `http://localhost:8081/registro`

2. **Obtener un token JWT**  
   Utiliza Postman (o una herramienta similar) para realizar la autenticación y obtener el token JWT.

3. **Enviar el token en las peticiones**  
   Agrega el token en el header de tus solicitudes:  
   `Authorization: Bearer <tu_token_jwt>`

4. **Consumir los endpoints del CRUD**  
   Una vez configurado el token, puedes ejecutar las operaciones del CRUD.

> **Ejemplos de requests** disponibles en el archivo **Request api.txt**.
> **Datos de prueba de productos** disponibles en el archivo **inserts.sql**.

---

## Autor

- Laura Duhalde
