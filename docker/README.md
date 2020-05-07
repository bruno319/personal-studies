## Docker

### Inicialização
Para carregar e poder usar as funções do script bash execute:
```
source ./calc_manager.sh
```
Funções disponíveis
```
buildMicroservice
startMicroservice
stopMicroservice
statusMicroservice
```

### Endpoints
```
localhost:8282/calc/sum/{x}/{y}
localhost:8282/calc/sub/{x}/{y}
localhost:8282/calc/mul/{x}/{y}
localhost:8282/calc/div/{x}/{y}
localhost:8282/calc/history
```
