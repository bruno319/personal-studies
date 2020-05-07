## Jenkins

### Configuração

* No console do Jenkins vá em _New Item_;
* Digite o nome do job _( bake / launch )_;
* Selecione _Pipeline_ e _Ok_;
* Na _Pipeline_, no campo _Definition_ selecione a opção _Pipeline script from SCM_;
* No campo _SCM_ selecione Git;
* Em _Repository URL_ cole o link para o repositório;
* Adicione as devidas credenciais no campo _Credentials_;
* No campo _Branches to build_ coloque */tema-12-jenkins ;
* No campo _Script Path_ coloque bruno-vieira/tema-12/_{nome do job}_/Jenkinsfile ;
* Salve o job e execute-o;

### Endpoints
```
localhost:8282/calc/sum/{x}/{y}
localhost:8282/calc/sub/{x}/{y}
localhost:8282/calc/mul/{x}/{y}
localhost:8282/calc/div/{x}/{y}
localhost:8282/calc/history
```
