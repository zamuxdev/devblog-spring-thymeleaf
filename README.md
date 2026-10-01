# 🚀 DevBlog - Spring Boot Web App

Un sistema de gestión de contenido (Blog) full-stack desarrollado para implementar una arquitectura MVC con Spring Boot, renderizado del lado del servidor con Thymeleaf y diseño responsivo con Bootstrap 5.

## 🛠️ Tecnologías Utilizadas

* **Backend:** Java 21, Spring Boot 4 (Web MVC, Data JPA, Validation)
* **Base de Datos:** PostgreSQL
* **Frontend:** Thymeleaf (con plantillas y fragmentos), HTML5
* **Estilos:** Bootstrap 5 (Flexbox, CSS Grid)
* **Herramientas:** Maven, Lombok

## ✨ Características Implementadas

* **CRUD Completo:** Creación, lectura, actualización y eliminación de publicaciones en la base de datos.
* **Reutilización de Vistas:** Un único formulario (`formulario.html`) maneja tanto la creación como la edición de posts según si el post ya tiene ID.
* **Validación de Datos (Backend):** Uso de anotaciones como `@NotBlank` y `@Size` para evitar registros en blanco o inválidos, mostrando el mensaje de error junto al campo correspondiente.
* **Fechas automáticas:** La fecha de creación se asigna con `@PrePersist` y se conserva al editar; la fecha de última edición se registra con `@PreUpdate`.
* **Eliminación segura:** El borrado se hace con una petición `POST` (no con un enlace `GET`), con confirmación previa en el navegador.
* **Diseño Responsivo (Mobile-First):** Interfaz fluida que se adapta desde pantallas ultra anchas hasta dispositivos móviles gracias al sistema de columnas de Bootstrap y etiquetas `viewport`.
* **Código DRY (Don't Repeat Yourself):** Fragmentos de Thymeleaf (`fragments.html`) para modularizar la cabecera, barra de navegación y carga de scripts.

## 📂 Estructura del Proyecto

```text
src/main/
├── java/com/devent/blog/
│   ├── BlogApplication.java
│   ├── controller/BlogController.java
│   ├── model/Post.java
│   └── repository/PostRepository.java
└── resources/
    ├── application.properties        (local, no se sube al repositorio)
    ├── application-prod.properties   (producción, usa variables de entorno)
    ├── static/css/styles.css
    └── templates/
        ├── fragments.html
        ├── index.html
        ├── formulario.html
        └── detalle.html
```

## 🚀 Guía de Instalación y Uso

Prerrequisitos

* Java 21 o superior
* PostgreSQL corriendo localmente en el puerto 5432
* Maven (opcional: el proyecto incluye Maven Wrapper `mvnw`)

Pasos para ejecutar localmente

1. **Clonar el repositorio:**
```bash
git clone https://github.com/zamuxdev/devblog-spring-thymeleaf.git
cd devblog-spring-thymeleaf
```
2. **Preparar la Base de Datos:**<br>
   Abre tu consola de PostgreSQL (psql) o cliente preferido y ejecuta:
```sql
CREATE DATABASE blog_db;
```
3. **Configurar Credenciales:**<br>
   Crea el archivo `src/main/resources/application.properties` (está en `.gitignore` para no publicar contraseñas) con tus datos locales:
```properties
spring.application.name=blog
spring.datasource.url=jdbc:postgresql://localhost:5432/blog_db
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```
4. **Compilar y Ejecutar:**<br>
   Desde la raíz del proyecto:
```bash
./mvnw spring-boot:run
```
   En Windows: `mvnw.cmd spring-boot:run`

5. **Acceder a la aplicación:**<br>
   Abre tu navegador web y visita http://localhost:8080

**Desarrollado por:** Fernando González Zamudio — [@zamuxdev](https://github.com/zamuxdev)
