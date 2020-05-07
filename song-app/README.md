## Song App

### Requisitos
- Java instalado
- MongoDb instalado
- Gradle instalado

### Instruções
- Gerar wrapper do gradle
```
gradle wrapper
```
- Rodar os testes
```
./gradlew test
```
- Iniciar aplicação
```
sh init.sh
```

## Endpoints
### Song Service 
_Instâncias na porta 8090 e 8092_

- Salva música
```
<POST>   localhost:8090/songs
```
Body
```
{
    "name": "nome da música"
}
```
- Busca todas músicas
```
<GET>   localhost:8090/songs
```
- Busca por ID
```
<GET>   localhost:8090/songs/{id}
```
### Playlist Service
- Cria playlist 
```
<POST>   localhost:9000/playlists?name={MyPlaylist}
```
- Busca todas playlists
```
<GET>   localhost:9000/playlists
```
- Busca por ID
```
<GET>   localhost:9000/playlists/{id}
```
- Adiciona música na playlist
```
<POST>   localhost:9000/playlists/{playlistID}?songId={IDdaMúsica}
```
- Remove música da playlist
```
<DELETE>   localhost:9000/playlists/{IDdaPlaylist}?songId={IDdaMúsica}
```
### App Service
- Busca músicas da playlist
```
<GET>   localhost:9010/app/v1/{IDdaPlaylist}
```
### Eureka Server
```
http://localhost:8761/
```
### Hystrix Dashboard
```
http://localhost:8761/hystrix
```
### Turbine Stream
```
http://localhost:8761/turbine.stream
```
Vídeo: https://www.youtube.com/watch?v=T1BfvwYutTs
