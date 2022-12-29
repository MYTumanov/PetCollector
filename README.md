# PET COLLECTOR

**JAVA**: 17

**Запуск**
```
cd ./pet-collector-server
mvn install
docker build --pull --rm -f "Dockerfile" -t petcollector:latest . 
docker compose -f "docker-compose.yml" up -d --build
```

```
java -jar ./target/PetCollector-0.0.1-SNAPSHOT.jar
```
