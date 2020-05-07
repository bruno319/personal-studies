## GCP 
Launch da calculadora na GCP usando App Engine e 
Compute Engine com Terraform, Packer e Ansible.

### App Engine
* Necessário ter instalado `google-cloud-sdk` e 
`google-cloud-sdk-app-engine-go` 
* Entre na pasta app-engine
* Conceda permissão ao script de inicialização
```
chmod +x app
```
* Execute o script
```
./app
```
 * Siga os passos para criação do app
 * **Endpoints**
```
<app_address>/calc/sum/{x}/{y}
<app_address>/calc/sub/{x}/{y}
<app_address>/calc/mul/{x}/{y}
<app_address>/calc/div/{x}/{y}
<app_address>/calc/history
```

### Compute Engine
Launch com terraform, packer e ansible
* Entre na pasta compute-engine
* Conceda permissão ao script de inicialização
```
chmod +x launch_script
```
* Carregue o script no terminal e passe o id do projeto e o arquivo json com as credenciais da gcp
```
source launch_script [project_id] [/path/to/credentials/]
```
* As funções do script ficarão nesse terminal.

`bake` - Cria a imagem

`launch` - Cria os recursos na cloud

`destroy` - Remove os recursos na cloud

 * **Endpoints**
```
<nlb_address>/calc/sum/{x}/{y}
<nlb_address>/calc/sub/{x}/{y}
<nlb_address>/calc/mul/{x}/{y}
<nlb_address>/calc/div/{x}/{y}
<nlb_address>/calc/history
```
