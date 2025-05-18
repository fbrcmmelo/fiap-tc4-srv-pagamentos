# fiap-tc4-srv-pagamentos

## Requisitos:

- Ter o Docker e o Docker Compose instalados.

- Subir o banco mongo e o app de pagamento.

```bash 
  docker-compose up
```

- Derrubar os containers:

```bash
  docker-compose down
```

## Acesse a aplicação

- http://localhost:8083/pagamento

## Gerar relatório Jacoco:

```bash
  mvn test jacoco:report
```

- Acesse o relatório em:
  target/site/jacoco/index.html