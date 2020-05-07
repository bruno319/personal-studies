# Hystrix
- Dashboard
```
localhost:<port>/hystrix
```
- Stream
```
localhost:<port>/actuator/hystrix.stream
```

## Microservices com Spring

- Build com gradle
```
./gradlew build
```
- Run com Docker e Docker-Compose
```
docker-compose up
```
### Endpoint
```
GET  -  localhost:8080/aggregator?githubUsername=Usuario&twitterScreenName=Usuario
```
#### Parâmetros da Requisição
- githubUsername -> nome de usuário no Github
- twitterScreenName -> nome de usuário no Twitter _(aquele que começa com @)_
