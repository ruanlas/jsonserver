# Mock de API
## Sobre o projeto

Este projeto foi construído para desenvolvedores que precisam de algum
mock de API para poder realizar testes de integração no backend ou
testes no frontend. Atualmente esta API pode oferecer alguns endpoints
customizáveis para operações `GET`, `POST`, `PUT` e `DELETE` de acordo
com a necessidade de cada desenvolvedor.

## Como esta API funciona

Os endpoints (rotas) desta API, bem como toda operação de `CRUD` que é
realizada nela é persistida em arquivo, o que significa que ela não faz
uso de nenhum banco de dados.
Desta forma, o desenvolvedor pode criar seus endpoints e também os objetos
que ficarão disponíveis para consulta.

## Configurando a execução da API

### Ambiente de desenvolvimento

No arquivo `application-dev.properties` possui 8 variáveis que o
desenvolvedor deve definir antes de subir o servidor:
* **`folder-router`** : o nome da pasta que armazenará o arquivo contendo
o registro dos endpoints disponíveis. Por padrão está definido como `router`
* **`filename-router`** : o nome do arquivo que conterá o registro dos
endpoints. Por padrão está definido como `router`
* **`fileextension-router`** : o nome da extensão do arquivo que conterá
o registro dos endpoints. Atualmente é suportado apenas o formato `json`
* **`folder-store`** : o nome da pasta que armazenará os objetos de interação
dos endpoints. Por padrão está definido como `datastore`
* **`filename-store-default`** : o nome padrão do arquivo que armazenará
os objetos de cada endpoint. Por padrão está definido como `data`
* **`fileextension-store-default`** : a extensão do arquivo que armazenará
os objetos de cada endpoint. Atualmente é suportado apenas o formato `json`
* **`application-port`** : a porta padrão que a API irá rodar. Por padrão
está definido na porta `8080`
* **`application-folder-root`** : o caminho para a pasta raiz da aplicação.
Por padrão está definido como `/tmp/jsonserverfolder`.
**OBS**: É importante que neste caminho seja dada a permissão de leitura e
escrita na pasta para que a aplicação consiga interagir com os arquivos.

### Ambiente de produção

O projeto possui 8 variáveis de ambiente (semelhantes às do ambiente de
desenvolvimento) que precisam ser definidas para que a aplicação consiga
ser executada:
* **`FOLDER_ROUTER`** : o nome da pasta que conterá o arquivo referente
aos endpoints
* **`FILENAME_ROUTER`** : o nome do arquivo que conterá o registro dos
endpoints
* **`FILEEXTENSION_ROUTER`** : o nome da extensão do arquivo que conterá
o registro dos endpoints. Atualmente é suportado apenas o formato `json`
* **`FOLDER_STORE`** : o nome da pasta que armazenará os objetos de interação
dos endpoints
* **`FILENAME_STORE_DEFAULT`** : o nome padrão do arquivo que armazenará
os objetos de cada endpoint
* **`FILEEXTENSION_STORE_DEFAULT`** : a extensão do arquivo que armazenará
os objetos de cada endpoint. Atualmente é suportado apenas o formato `json`
* **`APPLICATION_PORT`** : a porta padrão que a API irá rodar
* **`APPLICATION_FOLDER_ROOT`** : o caminho para a pasta raiz da aplicação.
**OBS**: É importante que neste caminho seja dada a permissão de leitura e
escrita na pasta para que a aplicação consiga interagir com os arquivos.

## Configurando os endpoints da API

Para configurar os endpoints da API é necessário antes estar com o projeto
sendo executado. Depois de subir o projeto, basta fazer uma chamada `POST`
para o endpoint **`/_routes`** passando no body um `json` com o campo
`"routes"` sendo um array de string contendo os endpoints que o desenvolvedor
deseja ter nesta API. Segue abaixo o exemplo do body esperado na requisição:
```
{
    "routes": [
        "endpoint1",
        "endpoint2",
        "endpoint3"
    ]
}
```
Para verificar quais endpoints foram definidos pelo desenvolvedor, basta apenas
fazer uma chamada `GET` para este mesmo endpoint (**`/_routes`**) que será
retornado um `json` no mesmo formato acima. Caso não tenha cadastrado nenhum
endpoint ainda, é retornado uma página com `status_code` `404`, contendo
os seguintes campos:
```
{
    "timestamp": 1599522559620,
    "status": 404,
    "error": "NOT_FOUND",
    "message": "Não foi possível encontrar o arquivo de rotas.",
    "path": "/_routes"
}
```
### IMPORTANTE

Por enquanto esta API dá suporte apenas para endpoints que contenham apenas uma `path`.
Desta forma, os endpoints a serem definidos **NÃO** podem seguir os exemplos semelhantes
aos informados abaixo:
```
"/path1/path2/path3"
"/path1/{id}/path3"
"/path1/path2/{id}"
```

Ao criar um endpoint pela rota `/_routes` automaticamente é disponibilizado os métodos
HTTP `POST`, `GET`, `DELETE` e `PUT` sobre o mesmo endpoint, conforme a representação
abaixo:
```
    GET      => "/{endpoint1}" 
    POST     => "/{endpoint1}"
    GET      => "/{endpoint1}/{id}"
    PUT      => "/{endpoint1}/{id}"
    DELETE   => "/{endpoint1}/{id}"
```
Note que ao criar um endpoint, é possível acessar um objeto cadastrado nele pelo seu `id`.
O `id` gerado para cada objeto cadastrado através de um `POST` é um UUID, sendo uma string
única para cada um.

#### Erros durante as requisições e respectivos retornos

Esta api está preparada para alguns erros comuns que podem acontecer durante as requisições.
Os possíveis cenários de erros são:
* Acessar um endpoint que não existe. Neste caso, será retornado:
```
{
    "timestamp": 1599529606242,
    "status": 404,
    "error": "NOT_FOUND",
    "message": "Este endpoint não existe.",
    "path": "/naoexiste"
}
```
* Acessar um objeto que não existe de um endpoint existente. É retornado neste caso o seguinte
erro:
```
{
    "timestamp": 1599529673982,
    "status": 404,
    "error": "NOT_FOUND",
    "message": "Não foi encontrado a informação com o id=[ 998 ]",
    "path": "/carros/998"
}
```
* Acessar um endpoint sem ter cadastrado os endpoints para a API na rota `/_routes`. Neste
caso, o seguinte erro é retornado:
```
{
    "timestamp": 1599524654739,
    "status": 500,
    "error": "INTERNAL_SERVER_ERROR",
    "message": "Não foi possível encontrar o arquivo de rotas para validar o endpoint.",
    "path": "/teste"
}
```

**Nesta versão da API, não é possível realizar nenhum tipo de filtro customizado, nem fazer
ordenação.**

## Gerando a imagem docker

Para gerar a imagem docker do projeto, basta apenas executar os comandos abaixo:
```
> mvn versions:set -DnewVersion=0.0.?
> mvn clean package dockerfile:build
```
Caso queira enviar a imagem para algum repositório de imagens, basta fazer o login
e fazer o push da imagem:
```
> docker login -u'<usuario>' -p'<senha>' <localizacao_do_repositorio>
> mvn dockerfile:push
```
