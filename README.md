## Jhonatan William Voltolini

## Observações:
- Utilizei o Postgres;
- Fiz o trabalho com base nos exemplos disponibilizados (laboratórios de 1 a 8) e, no conteúdo visto em sala de aula;
- Acredito que consegui atender a todo o escopo do trabalho, com algumas pequenas ressalvas que comentei nos tópico abaixo;
- De forma geral, tudo funcionou como o esperado, especialmente a parte do OAuth2 e JWT e as pwemissões;

## Usuários:
- Usuário: user - Senha: unidavi -- Tipo USER
- Usuário: manager - Senha: unidavi -- Tipo MANAGER
- Usuário: admin - Senha: unidavi -- Tipo ADMIN

# Descrição da Atividade:

## Deverão ser implementadas as seguintes funcionalidades:
- Cadastro de clientes e produtos - OK
- Pesquisa de clientes por endereço (rua, cidade, estado) - OK
- Pesquisa de produtos contendo um determinado nome - OK
- Pesquisa de produtos por marca - OK
- Cadastro de pedidos com determinados itens para produtos existentes - OK
- Pesquisa de produtos pela data de criação - OK (Ressalva: Acho que por questão de formatação de String/Date, a consulta não funciona corretamente, mas fiz toda a estrutura)

## Serão observados os seguintes conceitos nesta implementação:
- Implementação com Spring Boot com Java - OK
- Utilização conceitos RESTful e HATEOAS - OK
- Persistência das informações em um banco de dados (pode ser banco H2 em memória) - OK
- Documentação com uso do Swagger - OK (Ressalva: Não sei se fiz do modo correto, mas de forma geral funcionou)
- Implementação de teste unitário (para pelo menos um controller) - OK (Ressalva: Fiz testes simples, acredito que estão dentro do esperado)
- Proteção com práticas de segurança OAuth2 e JWT - OK