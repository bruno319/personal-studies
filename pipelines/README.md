## Pipelines

### Pré-requisitos
- Docker instalado e conta no DockerHub
- Packer instalado
- Ansible instalado
- Jenkins instalado
- JFrog Artifactory como repositório dos arquivos 

_Instalei o Jfrog como um [container Docker](https://www.jfrog.com/confluence/display/RTF/Installing+with+Docker)_
```
docker pull docker.bintray.io/jfrog/artifactory-cpp-ce
docker run --name artifactory -d -p 8081:8081 docker.bintray.io/jfrog/artifactory-cpp-ce:latest
```
### Váriaveis de ambiente
No Jenkins, é necessário adicionar as seguintes váriaveis de ambiente:

|  Variável | Conteúdo |
| :---: | :---: |
| JFROG_SERVER | url do o Jfrog Artifactory |
| JFROG_USER | nome de usuário no Jfrog Artifactory |
| JFROG_PASSWORD | senha do usuário no Jfrog Artifactory |
| DOCKER_HUB_USER | nome de usuário no DockerHub |
| DOCKER_HUB_PASSWORD | senha do usuário no DockerHub |
| DOCKER_HUB_REPOSITORY | nome do repositório utilizado no DockerHub |

No menu lateral do Jenkins vá em Manage Jenkins > Configure System, marque a caixa Enviroment variables e adicione as variáveis acima.

### Instruções
- Para criar um job no Jenkins, no menu lateral vá em New Item, coloque o nome do job, 
marque a opção Pipeline e clique em Ok. 
- Na guia Pipeline, em Definition escolha a opção Pipeline script from SCM. 
- No campo SCM escolha Git, e cole o link do repositório no campo Repository URL _(no caso eu usei este [repositório](https://github.com/BrunoVieira319/devops))_.
- No campo Script Path, defina o caminho para o Jenkinsfile no qual o Job irá usar. No reposítório, os arquivos Jenkinsfile estão nas pastas _build, infra_ e _run_, então é só criar um job para cada pasta.
- Salve o job.
- Para rodar os jobs, clique no nome dele e depois em Build Now no menu lateral.

### Informações adicionais
- O projeto que está sendo buildado é o da [Pet-Store + Tomcat](https://github.com/BrunoVieira319/petstore)
- Vídeo do funcionamento do projeto: https://youtu.be/fNm0rG4Rchs
