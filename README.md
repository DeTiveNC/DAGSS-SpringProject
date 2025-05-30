## Previo

### Requisitos previos

- Servidor de BD MySQL
- Maven (versión > 3.5.x)
- Docker
- (opcional) GIT
- (opcional) IDE Java (Eclipse, Netbeans, IntelliJ)

**Nota:** En los equipos de laboratorio, es conveniente establecer la variable de entorno JAVA_PATH, para que el comando `mvn` (Maven) compile y ejecute los proyectos siempre con el mismo JDK.

```sh
export JAVA_HOME=/usr/lib/jvm/jdk-20

export PATH=$JAVA_HOME/bin:$PATH
```

### Inicializar Mysql con PhpMyAdmin para configuración más rapida

- **Docker compose up** : en la carpeta y esperar a que inicialize

### Crear BD para los ejemplos (si no se ha hecho antes)

- Crear BD "recetas" en MySQL
  
```sh
mysql -u root -p    [pedirá la contraseña de MySQL]

mysql> create database recetas; 
mysql> create user recetas@'%' identified by "recetas";
mysql> grant all privileges on recetas.* to recetas@'%';

```
**Note:** **No es recomendable el '%' porque podrían acceder de forma externa**

Adicionalmente, puede ser necesario establecer un formato de fecha compatible

```
mysql> set @@global.time_zone = '+00:00';
mysql> set @@session.time_zone = '+00:00';
```

## CREAR PROYECTO SPRING BOOT

Existen varias alternativas

- Crear un proyecto Maven vacío e incluir las dependencias de los _starters_ de Spring Boot
- Usar Spring Tool Suite ([https://spring.io/tools](https://spring.io/tools)) y crear un nuevo proyecto _String Starter project_
- Crear el proyecto desde Spring Initializr ([https://start.spring.io/](https://start.spring.io/))

### Características del proyecto

```
Project: Maven Project
Language: Java
Spring Boot version: 3.1.5

Proyecto:
   groupId: es.uvigo.dagss
   artefactId: recetas
   package: es.uvigo.mei.dagss
   name: Recetas

Packaging: Jar
Java version: 21

Dependencias a incluir:
    Spring Web
    Spring Data JPA
    MySQLDriver
    Swagger
    Spring HATEOAS  (opc)
    Lombook  (opc)
```

### Propiedades de conexión con BD

En `src/main/resources/application.properties`

```sh
nano src/main/resources/application.properties


spring.datasource.url=jdbc:mysql://localhost:3306/recetas
spring.datasource.username=recetas
spring.datasource.password=recetas
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImplle
springdoc.api-docs.path=/swagger
springdoc.swagger-ui.path=/swagger
springdoc.packages-to-scan=es.uvigo.dagss.recetas.controlador
springdoc.api-docs.enabled=true
```

###   MERE   ###
![MERE](https://github.com/DeTiveNC/DAGSS-SpringProject/assets/116792124/8455bbd9-5e47-4c6b-b5a4-e6c7b28381e2)

