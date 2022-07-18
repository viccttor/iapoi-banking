# 🏦 IAPOI BANKING API 🏦

#### ✒️Autor - [Victor Henrique](https://www.linkedin.com/in/viccttor/)
- OBS: Em desenvolvimento 🚀🚀🚀

##  🛠️ Tecnologias e Ferramentas

* Java 11
* Spring Boot
* JPA Hibernate
* PostgreSQL
* Swagger
* Lombok
* JUnit5
* Intellij

##  Principais Mudanças

* Ferramentas
  * Utilização da IDEA Intelij
* Tecnologias
  * Lombok
  * Testes Unitários utilizando o JUnit5
* Conta
  * Opções de Conta - Corrente ou Poupança 
  * Status da Conta - Ativa ou Inativa
  * A Poupança não pode realizar movimentação(salvo RECEITA OU Regaste)
* Cadastros
  * Validação de CPF no cadastro
  * CPF, e-mail e telefone - São únicos por cliente
* Métodos
  * Refatoração dos métodos para retornar String para o usuário
  *  Tratamentos de erros (Corrigir)


## 📝 Diagrama de classes
 


![diagrama](https://github.com/viccttor/iapoi-banking-api/blob/main/sprint02/dg.png)

## Estrutura da API

### Cliente Controller

|Rest | URL                    |Função |
------ |------------------------| ------- |
PUT   | /inativar-conta        | Cadastrar um novo cliente |
PUT   | /ativar-conta          | Cadastrar um novo cliente |
POST   | /cadastrar-cliente     | Cadastrar um novo cliente |
GET    | /listar-todos-clientes | Listar todos os clientes |
GET    | /buscar-saldo          | Mostrar saldo em conta |
GET    | /buscar-cliente        | Buscar cliente por cpf |
DELETE | /deletar-cliente       | Deletar um cliente |

### Endereco Controller

|Rest | URL               |Função |
------ |-------------------| ------- |
PUT   | /alterar-endereco | Alteração de endereço cadastrado |
GET   | /listar-todos-enderecos | Listar todos os endereços |
GET   | /endereco-cliente | Busca de endereço por id |

### Movimentação Controller

|Rest | URL                         | Função                               |
------ |-----------------------------|--------------------------------------|
POST   | /saque                      | Realizar um saque                    |
POST   | /resgate-poupanca           | Realizar resgate total da poupança   |
POST   | /nova-movimentacao          | Gerar uma nova movimentação          |
POST   | /entre-contas               | Gerar uma transferência entre contas |
GET    | /movimentacoes-por-conta    | Listar movimentações por id          |
GET    | /listar-todas-movimentacoes | Listar todas as movimentações        |

### URL para Swagger 
http://localhost:8080/iapoi-banking-api/swagger-ui/index.html#/apps/cliente

<div>
 <a href="https://iapoi-banking.herokuapp.com/iapoi-banking-api/swagger-ui/index.html" target="_blank"><img src="https://img.shields.io/badge/Heroku-430098?style=for-the-badge&logo=heroku&logoColor=white" target="_blank"></a> 
</div>

## Utilizando a API

### Cadastros

* Cadastrando Cliente - 01

```
{
  "nome": "Victor Henrique",
  "cpf": "335.908.790-92",
  "email": "vhsmd7@gamil.com",
  "telefone": "81698798787",
  "tipoConta": "CORRENTE",
  "enderecoDto": {
    "cep": "55800-021",
    "cidade": "Campinas",
    "estado": "SP",
    "bairro": "Centro",
    "logradouro": "Rua Noruega",
    "numero": "120",
    "complemento": "Casa rosa"
  }
}
```
* Cadastrando Cliente - 02

```
{
  "nome": "Gleyson Sampaio",
  "cpf": "191.630.570-93",
  "email": "gleyson@gamil.com",
  "telefone": "28987546877",
  "tipoConta": "CORRENTE",
  "enderecoDto": {
    "cep": "64000-320",
    "cidade": "Teresina",
    "estado": "PI",
    "bairro": "Centro",
    "logradouro": "Rua Alvorada",
    "numero": "129B",
    "complemento": "Primeiro Andar"
  }
}
```
* Cadastrando Cliente - 03

```
{ 
  "nome": "Henrique",
   "cpf": "100.321.120-84",
  "email": "string1@gamil.com",
  "telefone": "64894551415",
  "tipoConta": "POUPANCA",
  "enderecoDto": {
    "cep": "01034-030",
    "cidade": "São Paulo",
    "estado": "SP",
    "bairro": "Centro",
    "logradouro": "Rua Abelardo Pinto Piolin",
    "numero": "11",
    "complemento": "Casa Cerãmica"
  }
}
```
* Cadastrando Cliente - 04

```
{
  "nome": "Teste Delete",
   "cpf": "900.452.340-51",
  "email": "teste@gamil.com",
  "telefone": "98745878521",
  "tipoConta": "POUPANCA",
  "enderecoDto": {
    "cep": "01034-030",
    "cidade": "São Paulo",
    "estado": "SP",
    "bairro": "Centro",
    "logradouro": "Rua 4 de Abril",
    "numero": "112",
    "complemento": "Casa Bege"
  }
}
```

### Deletar

* Deletendo o cliente 

```
id: 4

```

### Buscar 

* Buscando Todos os Clientes

* Buscando Todos os Endereços

### Alterando Endereco

* Alterando o endereço do cliente - 01 ERROR 500

```
  id: 1;
  
{
    "cep": "55800-021",
    "cidade": "Carpina",
    "estado": "PE",
    "bairro": "Centro",
    "logradouro": "Rua dos Tamarindos",
    "numero": "180",
    "complemento": "APT 2B - Primeiro Andar"
}


```

* Buscando Endereço - Por id

```
  CLiente 01 - id: 1
  CLiente 02 - id: 2
  CLiente 03 - id: 3

```

### Movimentações

* Gerando uma movimentação - Receita | CLiente 01

```
{
  "idConta": 1,
  "descricao": "Salário ",
  "valor": 3000.00,
  "formaMovimentacao": "TRANSFERENCIA",
  "tipoMovimentacao": "RECEITA"
 }
 
```

* Gerando uma movimentação - Receita | CLiente 02

```
{
  "idConta": 2,
  "descricao": "Salário ",
  "valor": 15000.00,
  "formaMovimentacao": "TRANSFERENCIA",
  "tipoMovimentacao": "RECEITA"
 }
 
```
* Gerando uma movimentação - Receita | CLiente 03

```
{
  "idConta": 3,
  "descricao": "Salário ",
  "valor": 1500.00,
  "formaMovimentacao": "TRANSFERENCIA",
  "tipoMovimentacao": "RECEITA"
 }
 
```
* Gerando uma movimentação - Despesa | CLiente 01

```
{
  "idConta": 1,
  "descricao": "TEMIDO BOLETO - FATURA",
  "valor": 1000.00,
  "formaMovimentacao": "PAGAMENTO",
  "tipoMovimentacao": "DESPESA"
 }
 
```
* Gerando uma movimentação - Despesa | CLiente 02

```
{
  "idConta": 2,
  "descricao": "NOVO CONTRATO DE INTERNET",
  "valor": 2000.00,
  "formaMovimentacao": "TRANSFERENCIA",
  "tipoMovimentacao": "DESPESA"
 }

```
* Gerando uma movimentação - Despesa | CLiente 03 | Não Permitida


```
Expectativa: "Operação Despesa não permitida para conta Poupança!"

{
  "idConta": 3,
  "descricao": "Teste - Conta Poupança",
  "valor": 100.00,
  "formaMovimentacao": "TRANSFERENCIA",
  "tipoMovimentacao": "DESPESA"
 }

```
* Gerando uma movimentação - Transferência entre contas | CLiente 02
- OBS:. Há duas descrições, pois no modelo de negócio foi decidio que o cliente poderá escolher entre repetir a 
descrição ou definir a descrição que ficará no remetente.

``` 
{ 
  "valor": 1000.00,
  "idContaPagadora": 2,
  "idContaRecebedora": 1,
  "descricaoPagador": "EMPRESTANDO DINHEIRO - VICTOR",
  "descricaoRecebedor": "DE GLEYSON - VICTOR, SEGUE O DINHEIRO SOLICITADO. 10 DIAS"
}

```

* Gerando uma movimentação - Transferência entre contas | CLiente 02

```
{
  "valor": 1001.00,
  "idContaPagadora": 1,
  "idContaRecebedora": 2,
  "descricaoPagador": "DEVOLVENDO DINHEIRO EMPRESTADO - GLEYSON",
  "descricaoRecebedor": "DE VICTOR - DEVOLUÇÃO, MESMO SABENDO QUE NÃO PRECISA :)"
}

```

* Buscando Movimentações - Por id

```
  CLiente 01 - id: 1
  CLiente 02 - id: 2
  CLiente 03 - id: 3

```

* Buscando Saldo da Conta por id

```
  CLiente 01 - id: 1
  CLiente 02 - id: 2
  CLiente 03 - id: 3

```

* Buscando Cliente por cpf

```
  CLiente 01 | cpf: 335.908.790-92
  CLiente 02 | cpf: 191.630.570-93
  CLiente 03 | cpf: 10032112084
```

### SAQUE

* Gerando uma movimentação - SAQUE | CLiente 01

```
  id: 1
  valor: 500
  
```


## Erros Tradados
### Endereco

* Alterando endereço | CLiente Inexistente

```
Expectativa: "Endereço não Encontrado!"

id: 20

{
  "cep": "string",
  "cidade": "string",
  "estado": "string",
  "bairro": "string",
  "logradouro": "string",
  "numero": "string",
  "complemento": "string"
}
```
* Estado com mais de dois caracteres

```
Expectativa: "Estado Inválido! Permitido apenas 2 caracteres!"

id: 1

{
  "cep": "string",
  "cidade": "string",
  "estado": "string",
  "bairro": "string",
  "logradouro": "string",
  "numero": "string",
  "complemento": "string"
}
```
* Numero com mais de 5 caracteres

```
Expectativa: "Numero Inválido! Permitido apenas 5 caracteres!"

id: 1

{
  "cep": "string",
  "cidade": "string",
  "estado": "PE",
  "bairro": "string",
  "logradouro": "string",
  "numero": "string",
  "complemento": "string"
}
```

### Cliente

* Inativar cliente com saldo

```
Expectativa: "Conta com saldo! Operação não permitida!"

id: 1

```
* Inativar / Ativar cliente Inexistente

```
Expectativa: "Conta não localizada!"

id: 1

```

* Cadastrando Cliente - Com CPF / email / Telefone já cadastrados

```
Tentativa 01 

Expectativa: Dados Inválidos! Verique os dados e tente novamente!
Dica: CPF ou/e email ou/e telefone já cadastrados ;)

{
  "nome": "Teste Delete",
   "cpf": "900.452.340-51",
  "email": "teste@gamil.com",
  "telefone": "98745878521",
  "tipoConta": "POUPANCA",
  "enderecoDto": {
    "cep": "01034-030",
    "cidade": "São Paulo",
    "estado": "SP",
    "bairro": "Centro",
    "logradouro": "Rua 4 de Abril",
    "numero": "112",
    "complemento": "Casa Bege"
  }
}

Tentativa 02 

Expectativa: Dados Inválidos! Verique os dados e tente novamente!
Dica: CPF ou/e email ou/e telefone já cadastrados ;)

{
  "nome": "Teste Delete",
   "cpf": "900.452.340-51",
  "email": "teste@gamil.com",
  "telefone": "98745878521",
  "tipoConta": "POUPANCA",
  "enderecoDto": {
    "cep": "01034-030",
    "cidade": "São Paulo",
    "estado": "SP",
    "bairro": "Centro",
    "logradouro": "Rua 4 de Abril",
    "numero": "112",
    "complemento": "Casa Bege"
  }
}

Tentativa 03

Expectativa: Dados Inválidos! Verique os dados e tente novamente!
Dica: CPF ou/e email ou/e telefone já cadastrados ;)

{
  "nome": "Teste Delete",
   "cpf": "532.084.050-06",
  "email": "teste@gamil.com",
  "telefone": "98745878521",
  "tipoConta": "POUPANCA",
  "enderecoDto": {
    "cep": "01034-030",
    "cidade": "São Paulo",
    "estado": "SP",
    "bairro": "Centro",
    "logradouro": "Rua 4 de Abril",
    "numero": "112",
    "complemento": "Casa Bege"
  }
}

Tentativa 04 

Expectativa: Dados Inválidos! Verique os dados e tente novamente!
Dica: CPF ou/e email ou/e telefone já cadastrados ;)

{
  "nome": "Teste Delete",
   "cpf": "532.084.050-06",
  "email": "delete@gamil.com",
  "telefone": "98745878521",
  "tipoConta": "POUPANCA",
  "enderecoDto": {
    "cep": "01034-030",
    "cidade": "São Paulo",
    "estado": "SP",
    "bairro": "Centro",
    "logradouro": "Rua 4 de Abril",
    "numero": "112",
    "complemento": "Casa Bege"
  }
}

Tentativa 05 

Expectativa: Cliente Teste Delete Cadastrado com Sucesso!

{
  "nome": "Teste Delete",
   "cpf": "532.084.050-06",
  "email": "@gamil.com",
  "telefone": "(81) 998 887 856",
  "tipoConta": "POUPANCA",
  "enderecoDto": {
    "cep": "01034-030",
    "cidade": "São Paulo",
    "estado": "SP",
    "bairro": "Centro",
    "logradouro": "Rua 4 de Abril",
    "numero": "112",
    "complemento": "Casa Bege"
  }
}

```

### Movimentação

* Gerando uma movimentação - Transferência entre contas | CLiente Inexistente Pagador

```
Expectativa: "ID da conta recebedora não existe!"

{
  "valor": 1001.00,
  "idContaPagadora": 1,
  "idContaRecebedora": 99,
  "descricaoPagador": "Teste da Conta Poupança",
  "descricaoRecebedor": "Teste da Conta Poupança"
}

```
* Gerando uma movimentação - Transferência entre contas | CLiente Inexistente Recebedor

```
Expectativa: "ID da conta Pagador não existe!"

{
  "valor": 1001.00,
  "idContaPagadora": 99,
  "idContaRecebedora": 1,
  "descricaoPagador": "Teste da Conta Poupança",
  "descricaoRecebedor": "Teste da Conta Poupança"
}

```
* Gerando uma movimentação - Transferência entre contas | Cliente 03 Poupança

```
Expectativa: "Operação não permitida para conta Poupança!"

{
  "valor": 1001.00,
  "idContaPagadora": 3,
  "idContaRecebedora": 1,
  "descricaoPagador": "Teste da Conta Poupança",
  "descricaoRecebedor": "Teste da Conta Poupança"
}

```
