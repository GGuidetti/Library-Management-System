** Para iniciar o projeto pela primeira vez é interessante rodar o userCreate.sql (a senha que esta sendo inserida na query é 'admin')

pois como o projeto não está contando com o CRUD de usuario implementado no FRONT, devido ao front ser um bonus como descrito nas instruções decidi nao fazer o sign up. 
Caso deseje testar, pode fazer as operações via postman também
como por exemplo

POST http://localhost:8080/app-users
{
  "name": "João Silva",
  "email": "joao.silva@exemplo.com",
  "password": "senha123",
  "registerDate": "2025-01-26T10:00:00",
  "phone": "11987654321"
}

a partir deste ponto o sistema está todo implementado no front-end. Incluindo a integração com google books

me adianto em dizer que o css não está perfeito pois dei uma importância menor ao mesmo para que o resto ficasse completo, visto novamente que o front nas instruções é apresentado como um 'bonus'


Utilizei o Postgres 17

