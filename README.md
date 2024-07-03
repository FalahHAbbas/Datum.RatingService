### Initial setup

#### 1. setup the database

```shell
# 1. Start docker container
docker-compose up -d
```

### 2.2.2. Run the application

```shell
# 2. Run the application
# 2.1. clean and package the application
mvn clean package

```

```shell
# 2.2. run the application
java -jar target/RatingService-0.0.1-SNAPSHOT.jar
```

### 2.2.3. Access the application

[Swagger](http://localhost:8088/swagger-ui/index.html)