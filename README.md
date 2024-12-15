# LiterAlura


![Portada del Proyecto](https://www.sydle.com/blog/assets/post/biblioteca-digital-63e521409e3c274f1050e336/capa%20biblioteca%20digital.png) 


![GitHub release (latest by date)](https://img.shields.io/github/v/release/usuario/literalura)
![GitHub release (last Commit)](https://img.shields.io/badge/last_commit-15%2F12%2F2024-brightgreen)
![GitHub issues](https://img.shields.io/github/issues/usuario/literalura)


## Índice

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Requisitos Previos](#requisitos-previos)
- [Importante](#importante)
- [Configuración del Proyecto](#configuración-del-proyecto)
- [Dependencias](#dependencias)
- [Funciones Implementadas](#funciones-implementadas)
- [Estado del Proyecto](#estado-del-proyecto)
- [Desarrolladores](#desarrolladores)
- [Conclusiones](#conclusiones)

## Descripción del Proyecto

**Literalura** es una aplicación desarrollada en Java con Spring Boot que utiliza la API de [Gutendex](https://gutendex.com) para gestionar información de libros. El proyecto permite almacenar libros seleccionados en una base de datos PostgreSQL, así como realizar consultas avanzadas basadas en autores, idiomas y fechas de vida de los autores.

## Requisitos Previos

- Java 17 o superior.
- Maven 3.6+.
- PostgreSQL 13 o superior.

## Importante

Es necesario configurar las variables de entorno para el correcto funcionamiento del proyecto:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/literalura
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

Estas variables permiten la conexión con la base de datos PostgreSQL y configuran el comportamiento de la aplicación.

## Configuración del Proyecto

1. Clonar el repositorio desde GitHub:
   ```bash
   git clone https://github.com/arturohdezc/literalura.git
   cd literalura
   ```
2. Configurar las variables de entorno en tu sistema operativo o en un archivo `.env`.
3. Instalar las dependencias utilizando Maven:
   ```bash
   mvn clean install
   ```
4. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## Dependencias

El proyecto utiliza las siguientes dependencias principales:

```xml
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
```

Asegúrate de incluirlas en el archivo `pom.xml`.

## Funciones Implementadas

- **Almacenar libros seleccionados:**
  Los datos de libros obtenidos desde la API de Gutendex se guardan en una base de datos PostgreSQL.

- **Mostrar todos los libros almacenados:**
  Endpoint que devuelve una lista de todos los libros registrados en la base de datos.

- **Consultar libros por autor:**
  Permite filtrar los libros almacenados según el nombre del autor.

- **Mostrar libros por idioma:**
  Realiza una búsqueda basada en el idioma de los libros almacenados.

- **Consultar libros según autores vivos en una fecha:**
  Dada una fecha, se muestran los libros cuyos autores estaban vivos en ese momento.

## Estado del Proyecto

El proyecto se encuentra en una fase funcional inicial, con todas las características mencionadas implementadas y probadas. Futuras actualizaciones incluirán mejoras en la interfaz de usuario y optimización del rendimiento.

## Desarrolladores

- Arturo Hernández

## Conclusiones

Literalura es una herramienta poderosa para gestionar información literaria con capacidades avanzadas de filtrado y almacenamiento. Al integrarse con la API de Gutendex y PostgreSQL, proporciona una solución escalable y eficiente para la gestión de datos de libros.
