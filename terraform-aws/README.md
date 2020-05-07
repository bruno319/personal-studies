## AWS 

Launch da calculadora na Aws usando Terraform, Packer e Ansible.

### Inicialização
* Entre na pasta do tema
* Conceda permissão ao script de inicialização
```
chmod +x launch_script
```
* Execute o script passando as suas chaves de acesso
```
./launch_script [access_key] [secret_key]
```
 
### Endpoints
```
<elb_adress>:8282/calc/sum/{x}/{y}
<elb_adress>:8282/calc/sub/{x}/{y}
<elb_adress>:8282/calc/mul/{x}/{y}
<elb_adress>:8282/calc/div/{x}/{y}
<elb_adress>:8282/calc/history
```
