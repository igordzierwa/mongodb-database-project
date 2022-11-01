# Northwind database - MongoDB

*Repository was transferred from an old account - [igordzie97](https://github.com/igordzie97/igordzie97)*

## Table of Contents
1. [Description](#description)
2. [Technologies](#technologies)
 - [Swagger](#swagger)
 - [Project Lombok](#project-lombok)
3. [Document-oriented database and relations](#document-oriented-database-and-relations)
4. [MongoDB database configuration](#mongodb-database-configuration)
5. [Containerization](#containerization)
6. [Addresses](#addresses)
7. [Documentation](#documentation)

## Description
The project purpose is to implement system executing basic operations on Northwind database. It is used MongoDB as an example of Document-oriented database.

### Database diagram
<img width="630" alt="129492965-a82a59f0-1ac1-4239-a86e-fd1f3c32f29c" src="https://user-images.githubusercontent.com/34041060/135817429-81c646f6-8139-455d-9f5c-a051b4197c2e.png">

## Document-oriented database and relations
There are two approaches to simulate relations in document-oriented datbase:
- **Embedded** - one document is embedded inside another document.
- **Reference** - documents are maintained separately, but one document contains a field that references to another document's id field.

In Northwind representation, relations between documents are simulated using embedded and reference approach:
- **Embedded** - saves complexity of queries, usually to one document.
- **Reference** - saves redundation of data in each query. It also ensures that size of the document won't be higher than 16MB.

Associative tables: Order Details, Employee Territories, CustomerCustomerDemo aren't included in the database.

## Technologies
- **MongoDB** – NoSQL database management system
- **Spring Boot + Java11** – Service executing basic database operations
- **Swagger** – Automated documentation for describing RESTful APIs expressed using JSON
- **Docker** – Containerization

### Swagger
Dependecies from pom.xml:
```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>3.0.0</version>
</dependency>
```
 
Configuration in SwaggerConfig class:
```java
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.agh.northwindproject"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Northwind Database Project")
                .description("Operations on northwind database using MongoDB + Spring")
                .build();
    }
}
```

### Project Lombok
Java library that automatically plugs into your editor and build tools. It replaces boilerplate code with easy to use annotations (constructors, getters, setters etc.).

Dependencies from `pom.xml` file:
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

## MongoDB database configuration
Dependencies from `pom.xml` file:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

Configuration file which is supported by Spring Boot - `application.properties`:
```properties
#APP CONFIG
server.port=8080
spring.application.name=northwind-service

#MONGODB CONFIGURATION
spring.data.mongodb.authentication-database=admin
spring.data.mongodb.username=springuser
spring.data.mongodb.password=Password
spring.data.mongodb.database=${MONGODB_DB:db_northwind}
spring.data.mongodb.host=${MONGODB_HOST:localhost}
spring.data.mongodb.port=${MONGODB_PORT:27017}

logging.level.org.springframework.data.mongodb.core.MongoTemplate=DEBUG
```

## Containerization
App is containerized using Docker - two containers are highlighted:
- Container with configured MongoDB database - `mongo`
- Container with server service - `northwind-service`

**Dockerfile:**
```dockerfile
FROM openjdk:11-jdk-slim
EXPOSE 8080
COPY . app/
RUN cd app; ./mvnw -Dmaven.test.skip package
ENTRYPOINT [ "java", "-jar", "/app/target/northwind-service.jar" ]
```

**Docker Compose:**
```docker-compose.yml
version: '3'
services:
  mongo:
    image: mongo:latest
    container_name: mongo
    environment:
      MONGO_INITDB_DATABASE: db_northwind
      MONGO_INITDB_ROOT_USERNAME: springuser
      MONGO_INITDB_ROOT_PASSWORD: Password
    ports:
      - '27017:27017'
    volumes:
      - mongodb-dbnorthwind-volume:/data/db
    networks:
      - northwind-service-network

  northwind-service:
    image: northwind-service
    container_name: 'northwind-service'
    build: northwind-service/.
    environment:
      MONGODB_HOST: mongo
    ports:
      - 8080:8080
    networks:
      - northwind-service-network

volumes:
  mongodb-dbnorthwind-volume:

networks:
  northwind-service-network:
```

`docker-compose up -d` - building containers.

`docker-compose down` - deleting containers.

## Addresses
- **Server:** http://localhost:8080
- **Swagger:** http://localhost:8080/swagger-ui/index.html

## Documentation
- [App documentation (in Polish):](https://github.com/igordzie97/mongodb-databases-project/blob/main/documentation/documentation.pdf)

## Authors:
- Igor Dzierwa
- Adrian Nędza
- Konrad Makuch
