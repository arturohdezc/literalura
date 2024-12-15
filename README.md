# LiterAlura


![Portada del Proyecto](https://www.sydle.com/blog/assets/post/biblioteca-digital-63e521409e3c274f1050e336/capa%20biblioteca%20digital.png) 


![GitHub release (latest by date)](https://img.shields.io/github/v/release/usuario/literalura)
![GitHub release (last Commit)](https://img.shields.io/badge/last_commit-15%2F12%2F2024-brightgreen)
![GitHub issues](https://img.shields.io/github/issues/usuario/literalura)


## Índice

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Importante](#importante)
- [Estado del Proyecto](#estado-del-proyecto)
- [Demostración de Funciones y Aplicaciones](#demostración-de-funciones-y-aplicaciones)
- [Requisitos del Sistema](#requisitos-del-sistema)
- [Configuración](#configuración)
- [Dependencias](#dependencias)
- [Desarrolladores](#desarrolladores)
- [Conclusiones](#conclusiones)

## Descripción del Proyecto
Literalura es una aplicación desarrollada en Java con el framework Spring Boot que interactúa con la API de Gutendex para gestionar información sobre libros. Las funcionalidades principales incluyen:

1. **Almacenar libros seleccionados** en una base de datos PostgreSQL.
2. **Mostrar todos los libros almacenados** en la base de datos.
3. **Filtrar libros por autor.**
4. **Mostrar libros de autores vivos en una fecha específica.**
5. **Filtrar libros por idioma.**

## Importante
Para que la aplicación funcione correctamente, es necesario configurar las siguientes variables de entorno en tu sistema:

```properties
spring.application.name=literalura
spring.datasource.url=jdbc:postgresql://${DB_HOST}/literalura
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true

server.port=8080
```

## Estado del Proyecto
Este proyecto está en fase de desarrollo inicial con las funcionalidades principales implementadas y probadas.

## Demostración de Funciones y Aplicaciones
- **Almacenar libros seleccionados:** Los datos de los libros se obtienen de la API de Gutendex y se guardan en la base de datos PostgreSQL.
- **Mostrar todos los libros:** Se listan todos los registros almacenados en la base de datos.
- **Filtrar por autor:** Filtra libros específicos según el autor seleccionado.
- **Filtrar por idioma:** Los libros pueden mostrarse según el idioma especificado.
- **Autoren vivos:** Introduciendo una fecha, se muestran los libros cuyos autores estaban vivos en ese año.

## Requisitos del Sistema
- Java 17 o superior.
- PostgreSQL 12 o superior.
- Maven 3.8 o superior.
- Conexión a internet para acceder a la API de Gutendex.

## Configuración
1. Clona el repositorio del proyecto en tu máquina local:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   ```
2. Configura las variables de entorno descritas en la sección [Importante](#importante).
3. Asegúrate de que PostgreSQL esté configurado y en funcionamiento.
4. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

## Dependencias
Incluye las siguientes dependencias en tu archivo `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.18.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Desarrolladores
- Arturo Hernández

## Conclusiones
Literalura es una herramienta poderosa para gestionar información literaria desde una API pública hacia una base de datos estructurada, con funcionalidades clave para filtrar y organizar datos de manera eficiente. ¡Es solo el comienzo de algo increíble!
