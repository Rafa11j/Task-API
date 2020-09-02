# Desafio Accenture 

## Configuração do Backend:

### Tecnologias Utilizadas:
 - Java 8
 - Spring boot
 - PostgreSQL
 - JPA
 - Hibernate
 - Flyway
 - JUnit
 - MockMvc
 

### Configuração
 
* Para rodar a aplicação com utilizar o docker, será necessário ter o `Docker` na sua máquina.
* Altere os dados de conexão do banco de dados no arquivo `docker-compose.yml`, que está localizado na raiz do projeto backend, para os da sua máquina. Mude apenas os seguintes valores:
```
 POSTGRES_PASSWORD=docker
 POSTGRES_USER=postgres
 POSTGRES_DB=db_desafio_accenture

 SPRING_DATASOURCE_URL=jdbc:postgresql://processapidatabase:5432/db_desafio_accenture
 SPRING_DATASOURCE_USERNAME=postgres
 SPRING_DATASOURCE_PASSWORD=docker
``` 

* Lembre de criar o seu banco de dados com o mesmo nome que você colocou no "url" no arquivo `docker-compose.yml`

* Após criar o banco de dados, pare o container do seu banco, para que não haja conflito na hora de levantar o container da aplicação.

* Por fim, execute esses 3 comandos:

Primeiro:
```
 ./mvnw package -DskipTests
```
Segundo:
```
 docker build ./ -t tasks-api
```
Terceiro:
```
 docker-compose up
```

* Feito isso, a aplicação criará as tabelas por conta das migrations.

* A aplicação estará disponível na url: [http://localhost:8080](http://localhost:8080)
