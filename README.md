# SistemaRH

API REST para gestão de escolas e professores, desenvolvida em Java com Spring Boot. O projeto organiza dados cadastrais do contexto educacional e aplica conceitos de persistência, arquitetura em camadas, validação e tratamento centralizado de exceções.

**Status:** em desenvolvimento. Os cadastros de escolas e professores possuem CRUD; os demais módulos têm modelagem inicial.

## Funcionalidades

| Módulo | Situação atual |
| --- | --- |
| Escolas | Cadastro, listagem, busca por ID, atualização e exclusão |
| Professores | Cadastro, listagem, busca por ID, atualização e exclusão |
| Disciplinas | Entidade, repositório e relacionamento com professores; sem endpoints próprios |
| Segmentos e turnos | Modelagem e associações com escolas |
| Lotações e usuários | Entidades iniciais; sem CRUD exposto |

A API utiliza DTOs para entrada e saída, valida os dados recebidos e retorna erros estruturados. O cadastro de professores possui restrições de unicidade para CPF, matrícula e email.

## Tecnologias

| Tecnologia | Uso |
| --- | --- |
| Java 21 | Linguagem e ambiente de execução |
| Spring Boot 4.1.1 | Configuração e execução da aplicação |
| Spring Web MVC | Endpoints REST |
| Spring Data JPA / Hibernate | Persistência e mapeamento das entidades |
| Jakarta Validation / Hibernate Validator | Validação dos DTOs |
| H2 Database | Banco em memória no perfil de desenvolvimento |
| Maven Wrapper | Build e gerenciamento de dependências |
| JUnit / Spring Boot Test | Infraestrutura de testes |

As versões e dependências estão declaradas no [pom.xml](pom.xml).

## Executar localmente

### Pré-requisitos

- JDK 21 configurado no `JAVA_HOME` e disponível no `PATH`.
- Git para clonar o repositório.
- Acesso à internet na primeira execução para baixar o Maven e as dependências.
- Postman ou outro cliente HTTP para explorar a API.

Não é necessário instalar o Maven separadamente: o repositório inclui o Maven Wrapper.

### Clonar

```bash
git clone https://github.com/Natanpython/SistemaRH.git
cd SistemaRH
```

### Iniciar a aplicação

No Windows, pelo PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

O perfil `dev` está ativo por padrão. Com a configuração atual, a API fica disponível em `http://localhost:8080`.

Para uma primeira consulta, faça `GET http://localhost:8080/escolas`.

## Banco de dados

O perfil de desenvolvimento utiliza H2 em memória e dados iniciais definidos em [import.sql](src/main/resources/import.sql), com escolas, segmentos, professores, disciplinas e associações.

Os dados alterados durante a execução são temporários: ao encerrar a aplicação, o banco em memória é perdido. Na próxima inicialização, os dados iniciais são carregados novamente.

O console H2 pode ser acessado em `http://localhost:8080/h2-console`:

| Campo | Valor |
| --- | --- |
| Driver Class | `org.h2.Driver` |
| JDBC URL | `jdbc:h2:mem:testdb` |
| User Name | `sa` |
| Password | Deixar em branco |

Configurações: [application.properties](src/main/resources/application.properties) e [application-dev.properties](src/main/resources/application-dev.properties).

## Endpoints

URL base: `http://localhost:8080`.

### Escolas

| Método | Rota | Operação | Sucesso |
| --- | --- | --- | --- |
| GET | `/escolas` | Listar escolas | `200 OK` |
| GET | `/escolas/{id}` | Buscar escola por ID | `200 OK` |
| POST | `/escolas` | Cadastrar escola | `201 Created` |
| PUT | `/escolas/{id}` | Atualizar escola | `200 OK` |
| DELETE | `/escolas/{id}` | Excluir escola | `204 No Content` |

### Professores

| Método | Rota | Operação | Sucesso |
| --- | --- | --- | --- |
| GET | `/professores` | Listar professores | `200 OK` |
| GET | `/professores/{id}` | Buscar professor por ID | `200 OK` |
| POST | `/professores` | Cadastrar professor | `201 Created` |
| PUT | `/professores/{id}` | Atualizar professor | `200 OK` |
| DELETE | `/professores/{id}` | Excluir professor | `204 No Content` |

As listagens retornam arrays sem paginação. O POST retorna o objeto criado e o cabeçalho `Location` com sua URL. O DELETE bem-sucedido não possui corpo de resposta.

## Exemplos no Postman

Selecione o método e a URL. Para POST e PUT, use **Body → raw → JSON**, com `Content-Type: application/json`.

### Cadastrar escola

`POST http://localhost:8080/escolas`

```json
{
  "nome": "Escola Municipal Aprender",
  "endereco": "Rua das Acácias, 120",
  "inep": "99000006",
  "quantidadeTurmas": 12
}
```

### Cadastrar professor

`POST http://localhost:8080/professores`

```json
{
  "nome": "Ana Souza",
  "cpf": "52998224725",
  "matricula": "PROF2026001",
  "cargaHoraria": 40,
  "formacao": "Licenciatura em Matemática",
  "posGraduacao": "Especialização em Educação",
  "contato": "11999998888",
  "email": "ana.souza@example.com"
}
```

Os exemplos são destinados a testes locais. `id` e `criadoEm` são definidos pela aplicação e não precisam ser enviados.

### Atualizar e excluir

Para atualizar, use `PUT /professores/{id}` ou `PUT /escolas/{id}`, substituindo `{id}` pelo identificador retornado no cadastro. Envie o corpo completo com todos os campos que deseja manter, incluindo os obrigatórios. Os campos opcionais omitidos podem ser substituídos por `null`; o PUT não funciona como atualização parcial. O ID e a data de criação são preservados.

Para excluir, envie `DELETE` para a mesma URL do recurso, sem corpo.

## Validações

### Professores

| Campo | Regra atual |
| --- | --- |
| `nome` | Obrigatório, de 3 a 80 caracteres |
| `cpf` | Obrigatório, validado por `@CPF` e único no banco |
| `matricula` | Obrigatória e única no banco |
| `cargaHoraria` | Obrigatória e maior que zero |
| `contato` | Obrigatório |
| `email` | Obrigatório, validado por `@Email` e único no banco |
| `formacao`, `posGraduacao` | Opcionais |

A validação do CPF verifica sua estrutura e dígitos verificadores; não consulta um cadastro oficial. O CPF ainda é salvo como recebido, sem normalização automática. Nos testes, utilize sempre apenas números para manter um padrão.

Os CPFs fictícios do `import.sql` são inseridos diretamente no banco e não passam pelas validações do DTO. Para atualizar esses professores pela API, envie um CPF que satisfaça `@CPF`.

### Escolas

| Campo | Regra atual |
| --- | --- |
| `nome` | Obrigatório, de 3 a 80 caracteres |
| `inep` | Obrigatório |
| `quantidadeTurmas` | Maior que zero quando informado |
| `endereco` | Opcional |

## Tratamento de erros

O [ControllerExceptionHandler](src/main/java/com/SistemaRh/sistemaRh/compartilhado/handlers/ControllerExceptionHandler.java) centraliza as respostas das exceções tratadas:

| Status | Situação |
| --- | --- |
| `400 Bad Request` | `DatabaseException`, utilizada para falhas de integridade na exclusão |
| `404 Not Found` | Recurso não encontrado |
| `409 Conflict` | `DataIntegrityViolationException`, incluindo conflitos de unicidade |
| `422 Unprocessable Entity` | Falha nas validações dos DTOs |

Exemplo ilustrativo de conflito:

```json
{
  "timestamp": "2026-09-10T15:00:00Z",
  "status": 409,
  "error": "Os dados informados conflitam com registros existentes.",
  "path": "/professores"
}
```

Nos erros de validação, a resposta também contém `errors`, com o campo e a mensagem:

```json
{
  "timestamp": "2026-09-10T15:00:00Z",
  "status": 422,
  "error": "Dados invalidos",
  "path": "/professores",
  "errors": [
    {
      "fieldName": "cargaHoraria",
      "message": "A carga horária deve ser positiva."
    }
  ]
}
```

Erros de leitura do JSON e outros casos não cobertos pelo handler podem usar o formato padrão do Spring.

## Organização do código

```text
src/
├── main/
│   ├── java/com/SistemaRh/sistemaRh/
│   │   ├── compartilhado/   # Exceções, respostas de erro e handler global
│   │   ├── escola/          # Controller, serviço, DTOs, entidades e repositório
│   │   ├── professor/       # CRUD de professores e modelagem de disciplinas
│   │   ├── usuario/         # Modelagem inicial de usuários
│   │   └── SistemaRhApplication.java
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       └── import.sql
└── test/                   # Testes automatizados
```

O fluxo das requisições passa pelo **Controller → Service → Repository → Banco de dados**. Os controllers recebem e devolvem DTOs; os serviços coordenam as operações e transações; os repositórios realizam o acesso às entidades persistidas.

## Testes e build

No Windows:

```powershell
.\mvnw.cmd test
.\mvnw.cmd package
```

No Linux ou macOS:

```bash
./mvnw test
./mvnw package
```

O teste automatizado atual verifica o carregamento do contexto Spring (`contextLoads`). Ainda não há uma suíte automatizada que cubra todas as operações do CRUD.

Após o build, execute o JAR:

```bash
java -jar target/sistemaRh-0.0.1-SNAPSHOT.jar
```

Para verificar manualmente o cadastro de professores:

1. Envie um POST com dados válidos e inéditos: espere `201`.
2. Consulte o ID retornado: espere `200`.
3. Atualize o professor com um corpo válido: espere `200`.
4. Repita o cadastro com os mesmos dados únicos: espere `409`.
5. Envie `cargaHoraria` igual a zero: espere `422`.
6. Exclua o professor criado: espere `204`.
7. Consulte novamente o ID excluído: espere `404`.

## Evolução do projeto

- Implementar o CRUD de disciplinas.
- Expor o gerenciamento dos vínculos entre professores e disciplinas.
- Completar os módulos de lotações e usuários.
- Normalizar o CPF antes da persistência.
- Ampliar a cobertura com testes de integração dos endpoints.

Esses itens representam trabalhos futuros. A versão atual expõe os cadastros básicos de escolas e professores, sem interface gráfica ou autenticação implementada.
