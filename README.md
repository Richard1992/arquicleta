# Arquicleta

Arquicleta es un proyecto modular que gestiona usuarios y grupos de manera independiente, usando una arquitectura Spring Boot. Cada módulo tiene su propia base de datos: **usuarios** utiliza MySQL y **grupos** utiliza PostgreSQL. El proyecto está desarrollado en **Java 17** y gestiona sus dependencias con **Maven**.

## Estructura del proyecto

El proyecto está dividido en los siguientes módulos:

- **usuarios**: Maneja la lógica y gestión de usuarios. Utiliza una base de datos MySQL para almacenar la información de los usuarios.
- **grupos**: Maneja la lógica y gestión de grupos. Utiliza una base de datos PostgreSQL para almacenar la información de los grupos.

### Tecnologías principales

- **Java 17**
- **Spring Boot**
- **Maven**
- **MySQL** para el módulo de **usuarios**
- **PostgreSQL** para el módulo de **grupos**
- **Docker** (para contenedores y bases de datos, opcional)

## Requisitos

Antes de comenzar, asegúrate de tener lo siguiente instalado en tu entorno de desarrollo:

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven 3.x](https://maven.apache.org/install.html)
- [MySQL](https://dev.mysql.com/downloads/)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Docker](https://www.docker.com/get-started) (opcional, para correr las bases de datos en contenedores)

## Instalación y configuración

### 1. Clonar el repositorio

Primero, clona el repositorio en tu máquina local:

```bash
git clone https://github.com/Richard1992/arquicleta.git
cd arquicleta-main
```

## 2. Configuración de las bases de datos

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/usuarios_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.datasource.url=jdbc:postgresql://localhost:5432/grupos_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 2. Ejecutar el proyecto

# Compilar el proyecto
```
mvn clean install
```

# Ejecutar el módulo de usuarios
```
cd usuarios
mvn spring-boot:run
```

# Ejecutar el módulo de grupos
```
cd ../grupos
mvn spring-boot:run
```

Usando Docker (opcional)

Puedes usar Docker para correr las bases de datos MySQL y PostgreSQL sin tener que instalarlas manualmente en tu sistema.

Ejemplo de un archivo docker-compose.yml para MySQL y PostgreSQL:

```
version: '3'
services:
  mysql:
    image: mysql:8
    environment:
      MYSQL_DATABASE: usuarios_db
      MYSQL_ROOT_PASSWORD: root_password
      MYSQL_USER: tu_usuario
      MYSQL_PASSWORD: tu_password
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql

  postgres:
    image: postgres:13
    environment:
      POSTGRES_DB: grupos_db
      POSTGRES_USER: tu_usuario
      POSTGRES_PASSWORD: tu_password
    ports:
      - "5432:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data

volumes:
  mysql-data:
  postgres-data:
```

```
docker-compose up -d
```
---

### **Explicación de las secciones del README.md:**

1. **Estructura del proyecto:** Describe brevemente los módulos y las tecnologías que se utilizan.
2. **Requisitos:** Detalla lo que necesitas para ejecutar el proyecto.
3. **Instalación y configuración:** Explica cómo clonar el repositorio y configurar las bases de datos.
4. **Ejecutar el proyecto:** Proporciona instrucciones para ejecutar el proyecto, tanto con Maven como con Docker.
5. **Endpoints:** Lista los principales endpoints de la API.
6. **Pruebas:** Describe cómo ejecutar las pruebas del proyecto.
7. **Contribuir:** Explica cómo contribuir al proyecto si otros desarrolladores quieren colaborar.
8. **Licencia:** Informa bajo qué licencia se distribuye el código.

Con este README.md, otros desarrolladores o usuarios podrán entender mejor cómo funciona tu proyecto y cómo usarlo. ¡Espero que te sirva!

