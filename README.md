# hospital-system
Post Tech Fiap - Arquitetura e Desenvolvimento em JAVA - Tech Challenger Fase 3

## FIAP - Tech Challenge - Fase 3


### Sistema de gestão para seus estabelecimentos

Nessa terceira fase de entrega o objetivo é desenvolver um backend simplificado e modular, com foco em comunicação assíncrona, garantindo que o sistema seja escalável e que utilize boas práticas de comunicação entre serviços  

### Serviços 

#### Serviço de agendamento 
Serviço responsável pela criação e edição das consultas 

Disponível na porta http://localhost:8080/

Link para a documentação das API's do projeto (OpenAPI):
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

#### Serviço de Notificações  
Serviço responsável por envia lembretes automáticos aos pacientes sobre consultas futuras ou alteração nas consultas 

Disponível na porta http://localhost:8080/

#### Serviço de Histórico 
Serviço responsável por buscar as consultas utilizando graphql 

Disponível na porta http://localhost:8082/


## Para executar a aplicação:

### 1. Fazer o build dos containeres da aplicação:
Executar o seguinte comando:
    
    docker-compose up --build

O comando acima gerará os conteineres de aplicação e banco de dados.

### 2. Executar a aplicação através dos containeres criados:
Executar o seguinte comando para inicializar os containeres da aplicação, na raíz do projeto (onde se encontra o arquivo docker-compose.yml):

    docker-compose up

### 3. Acessar a aplicação
A aplicação estará disponível nas seguintes URL:

    http://localhost:8080/
    http://localhost:8081/
    http://localhost:8082/

As collections do postman se encontram no seguinte link: [Collection Postman](https://github.com/MaiconFiuza/hospital-system/blob/main/Projeto%20M%C3%B3dulo%203.postman_collection.json)


### Cobertura de testes
Para cobertura de testes foi utilizado a ferramenta [Jacoco](https://www.eclemma.org/jacoco/)
Para rodar os testes unitários, na raiz do projeto, execute o seguinte comando: 

> mvn test

O relatório de cobertura pode ser encontrado dentro da pasta `./target`. Para acessar o relatório web acesse:

> taget/site/jacoco/index.html

### Vídeo de apresentação do projeto

O Vídeo de apresentação se encontra no seguinte [link](https://www.youtube.com/watch?v=mtklA93RSFQ)
