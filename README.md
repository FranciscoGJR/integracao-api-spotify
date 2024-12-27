# Executar projeto

## Requisitos

Baixar e instalar as seguintes ferramentas:

- `Java ^21`
- `Maven ^3.6.0`

## Configurações iniciais

Localizado no pacote `com.api`, o arquivo `Constantes.java` deve ser preenchido com as informações do token de acesso às API's do Spotify e também o path da cidade que será feito a busca.

## Iniciar aplicação

```
mvn spring-boot:run
```

Ao finalizar, será exibido no console a classificação de todos os gêneros.
