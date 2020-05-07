## Ansible

* Entre na pasta do tema 
* Crie a imagem do docker
```
docker build -t {nomeDaImagem} . 
```
* Suba um container com a imagem criada
```
docker run {nomeDaImagem}
```
_O container só executa o playbook e finaliza, se quiser testar os endpoints da 
calculadora é necessário rodar o playbook manualmente dentro do container._ 
```
docker run -it -p 8282:8282 {nomeDaImagem} /bin/bash
ansible-playbook playbook.yml
```

### Endpoints
```
localhost:8282/calc/sum/{x}/{y}
localhost:8282/calc/sub/{x}/{y}
localhost:8282/calc/mul/{x}/{y}
localhost:8282/calc/div/{x}/{y}
localhost:8282/calc/history
```
