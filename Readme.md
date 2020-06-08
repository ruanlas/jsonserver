# API para arquivos json

Esta API foi criada para desenvolvedores que necessitam obter
recursos de uma api que ainda está sendo implementada (ou seja,
ela ainda não está disponível). O intuito
deste servidor é disponibilizar dois endpoints, um para listagem
de recursos e outro para obter um determinado recurso. Os recursos
disponibilizados são totalmente customizáveis de acordo com a
necessidade do desenvolvedor.

## Como esta API funciona

Esta API disponibiliza os recusos que são lidos de um arquivo
json definido pelo desenvolvedor. O desenvolvedor deve então criar
uma lista de objetos json contendo os campos que irá necessitar.
Cada objeto que o desenvolvedor cria na lista deve ter um atributo ``id``
sendo único, onde o servidor irá utilizar como referência este campo
para realizar as buscas de objetos por id.

Exemplo do arquivo que o desenvolvedor deve criar:

```
[
    {
        "id": "1",
        "nome": "Paulo",
        "idade": "25 anos"
    },
    {
        "id": "2",
        "nome": "João",
        "idade": "33 anos"
    },
    {
        "id": "3",
        "nome": "Ricardo",
        "idade": "21 anos"
    }
]
```

O desenvolvedor pode salvar o arquivo com o nome que preferir.

## Endpoints

A API disponibiliza dois endpoints:
* `/data` : obtém a listagem de todos os objetos criados no arquivo
json
* `/data/{id}` : obtem o objeto correspondente ao `id` passado no path

## Configurando a API
No arquivo `application.properties` possui 3 configurações que o
desenvolvedor deve realizar antes de subir o servidor:
* `file.name` : o nome do arquivo contendo a lista de objetos json que
o desenvolvedor criou. Por padrão foi definido como `datafile.json`
* `folder.server.name` : o caminho que o arquivo está localizado 
dentro do servidor. Por padrão está definido como `/tmp/jsonserverfolder`.
OBS: É importante que neste caminho seja dada a permissão de leitura e
escrita na pasta para que a aplicação consiga ler o arquivo.
* `server.port` : a porta padrão que a API irá rodar. Por padrão está definida
como `8080`.