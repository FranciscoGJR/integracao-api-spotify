# Rodar o projeto

## Requisitos

Baixar e instalar as seguintes ferramentas:

- `Java 21^`
- `Maven 3.6.3^`

## Configurações iniciais

É necessário preencher no arquivo `Constantes.java`, localizado em no pacote `com.api`, o token de acesso às API's do Spotify e também o path da cidade que será feito a busca.

## Iniciar aplicação

```
mvn spring-boot:run
```

Ao finalizar, será exibido no console a classificação de todos os gêneros.
