# AutoManager — Micro-serviço de Gestão de Automotivos

Sistema de gerenciamento de usuários, veículos, vendas, mercadorias e serviços,
com autenticação e autorização via **JWT (Json Web Token)**.

---

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

| Ferramenta | Versão mínima |
|---|---|
| Java (JDK) | 21 |
| Maven | 3.8+ |
| IntelliJ IDEA / Eclipse / VS Code | qualquer versão recente |

---

## Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3.4.3**
- **Spring Security 6** — autenticação e autorização
- **JWT (JJWT 0.12.6)** — geração e validação de tokens
- **Spring Data JPA + Hibernate 6**
- **Banco H2** — banco em memória (sem necessidade de instalar nada)
- **MapStruct 1.5.5** — mapeamento de DTOs
- **Lombok 1.18.36** — redução de boilerplate
- **Spring HATEOAS** — links nos responses

---

## Como rodar

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd automanager
```

### 2. Compile e suba o projeto

```bash
mvn spring-boot:run
```

Ou rode direto pela IDE clicando em **Run** na classe `AutomanagerApplication.java`.

### 3. Acesse a API

A aplicação sobe na porta **8091**:

```
http://localhost:8091
```

> Caso a porta 8091 já esteja em uso, altere em `src/main/resources/application.properties`:
> ```properties
> server.port=OUTRA_PORTA
> ```

---

## Banco de dados H2

O projeto usa banco **em memória** — não precisa instalar nada.
Os dados são recriados a cada vez que a aplicação sobe.

Acesse o console visual do banco em:

```
http://localhost:8091/h2-console
```

| Campo    | Valor                        |
|----------|------------------------------|
| JDBC URL | `jdbc:h2:mem:automanager`    |
| User     | `sa`                         |
| Password | *(deixar vazio)*             |

---

## Autenticação — Como usar a API

A API é protegida por **JWT**. É necessário fazer login para obter o token
e usá-lo em todas as requisições.

### Passo 1 — Login

```
POST http://localhost:8091/login
Content-Type: application/json
```

Body:
```json
{
  "nomeUsuario": "admin",
  "senha": "admin123"
}
```

O token JWT será retornado no **header** da resposta:

```
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9...
```

### Passo 2 — Usar o token

Em todas as requisições, adicione o header:

```
Authorization: Bearer <seu_token_aqui>
```

No **Postman**: aba **Auth** → Type: **Bearer Token** → cole o token.

---

## Usuários pré-cadastrados

Ao subir a aplicação, 4 usuários são criados automaticamente:

| Usuário    | Senha          | Perfil          |
|------------|----------------|-----------------|
| `admin`    | `admin123`     | ADMINISTRADOR   |
| `gerente`  | `gerente123`   | GERENTE         |
| `vendedor` | `vendedor123`  | VENDEDOR        |
| `cliente`  | `cliente123`   | CLIENTE         |

---

## Níveis de acesso (Perfis)

| Perfil            | Permissões |
|-------------------|------------|
| **ADMINISTRADOR** | CRUD completo em todos os recursos, incluindo outros administradores |
| **GERENTE**       | CRUD em usuários (gerente/vendedor/cliente), serviços, vendas e mercadorias |
| **VENDEDOR**      | CRUD em clientes, leitura de serviços e mercadorias, criação de vendas |
| **CLIENTE**       | Leitura do próprio cadastro e das próprias vendas |

---

## Endpoints principais

| Método   | Endpoint          | Descrição                          |
|----------|-------------------|------------------------------------|
| `POST`   | `/login`          | Autenticação — retorna o token JWT |
| `GET`    | `/usuarios`       | Lista todos os usuários            |
| `POST`   | `/usuarios`       | Cadastra novo usuário              |
| `GET`    | `/usuarios/{id}`  | Busca usuário por ID               |
| `PUT`    | `/usuarios/{id}`  | Atualiza usuário                   |
| `DELETE` | `/usuarios/{id}`  | Remove usuário                     |
| `GET`    | `/veiculos`       | Lista veículos                     |
| `POST`   | `/veiculos`       | Cadastra veículo                   |
| `GET`    | `/vendas`         | Lista vendas                       |
| `POST`   | `/vendas`         | Cadastra venda                     |
| `GET`    | `/mercadorias`    | Lista mercadorias                  |
| `POST`   | `/mercadorias`    | Cadastra mercadoria                |
| `GET`    | `/servicos`       | Lista serviços                     |
| `POST`   | `/servicos`       | Cadastra serviço                   |
| `GET`    | `/empresas`       | Lista empresas                     |
| `POST`   | `/empresas`       | Cadastra empresa                   |
| `GET`    | `/documentos`     | Lista documentos                   |
| `GET`    | `/enderecos`      | Lista endereços                    |
| `GET`    | `/telefones`      | Lista telefones                    |
| `GET`    | `/emails`         | Lista emails                       |

> Todos os endpoints (exceto `/login`) exigem token JWT válido no header.

---

## Estrutura do projeto

```
src/main/java/com/autobots/automanager/
├── configuracao/        # Configuração do Spring Security
├── controller/          # Controllers REST
├── dtos/                # DTOs de request e response
├── enums/               # Enums (PerfilUsuario, TipoVeiculo, etc.)
├── exceptions/          # Tratamento de exceções
├── filtros/             # Filtros JWT (Autenticador, Autorizador)
├── jwt/                 # Geração, análise e validação do JWT
├── mappers/             # Mappers MapStruct (entidade <-> DTO)
├── model/entity/        # Entidades JPA
├── repository/          # Repositórios Spring Data
├── security/            # UserDetails e UserDetailsService
└── services/            # Regras de negócio (CRUD por entidade)
```

---

## Observações

- O banco H2 é **em memória**: os dados são **perdidos** ao reiniciar a aplicação.
- O token JWT expira em **24 horas** (configurável em `application.properties`).
- Para usar com MySQL/MariaDB em produção, basta alterar as configurações
  de datasource no `application.properties`.
