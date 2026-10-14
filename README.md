# EcoCycle API — DevOps

Projeto acadêmico ESG para gestão de resíduos, usuários e coletas, desenvolvido com Java 17, Spring Boot, Maven e Oracle Database.

## Como executar localmente com Docker

Pré-requisitos: Docker Desktop com Docker Compose e Git.

1. Copie `.env.example` para `.env`.
2. Altere `ORACLE_PASSWORD` no `.env` para uma senha local.
3. Na raiz do projeto, execute:

```bash
docker compose up --build -d
docker compose ps
docker compose logs -f api
```

A API é publicada em `http://localhost:8080`. Consulte os endpoints existentes nos controllers em `src/main/java`. O primeiro início do Oracle pode levar alguns minutos.

Para encerrar:

```bash
docker compose down
```

Para remover também o volume persistente do banco (isso apaga os dados locais):

```bash
docker compose down -v
```

## Pipeline CI/CD

Ferramenta: GitHub Actions, definida em `.github/workflows/ci-cd.yml`.

- **Build e testes:** em push e pull request para `main`, configura Java 17, usa cache Maven e executa `./mvnw -B clean verify`.
- **Imagem:** após sucesso dos testes, constrói e publica a imagem no GitHub Container Registry (GHCR).
- **Staging:** deploy SSH condicional quando `STAGING_HOST` estiver configurado nas Variables do repositório e os secrets de staging estiverem definidos.
- **Produção:** job separado com ambiente GitHub `production`, condicionado à configuração de `PRODUCTION_HOST`. Recomenda-se habilitar aprovação obrigatória nas proteções do Environment.

### Configuração necessária para deploy real

Criar GitHub Actions Variables: `STAGING_HOST`, `STAGING_USER`, `PRODUCTION_HOST`, `PRODUCTION_USER`.

Criar GitHub Actions Secrets: `STAGING_SSH_KEY`, `STAGING_DB_URL`, `STAGING_DB_USERNAME`, `STAGING_DB_PASSWORD`, `PRODUCTION_SSH_KEY`, `PRODUCTION_DB_URL`, `PRODUCTION_DB_USERNAME`, `PRODUCTION_DB_PASSWORD`.

É necessário ter servidores acessíveis por SSH, Docker instalado nesses servidores e bancos de dados de cada ambiente acessíveis. Sem isso, o workflow realiza build/testes e publicação da imagem, mas não realiza deploy real. Não registrar um deploy como concluído sem validar a execução.

## Containerização

O `Dockerfile` usa duas etapas: Maven + JDK 17 para compilar e JRE 17 para executar o JAR, reduzindo o tamanho da imagem final. O processo executa com usuário não-root e expõe a porta 8080.

O `docker-compose.yml` orquestra a API e Oracle XE, usa uma rede dedicada, volume persistente e variáveis de ambiente. As credenciais do arquivo `.env` são apenas para desenvolvimento local; não use senhas de exemplo em produção.

Comandos úteis:

```bash
docker compose build
docker compose up -d
docker compose ps
docker compose logs -f
docker compose down
```

## Prints do funcionamento

Inserir evidências reais após executar o projeto:
1. Saída de `docker compose ps` com os serviços saudáveis.
2. Execução do workflow no GitHub Actions mostrando build e testes aprovados.
3. Publicação da imagem no GHCR.
4. URL/print de staging funcionando.
5. URL/print de produção funcionando.

Não foram incluídas capturas fictícias. Os itens de deploy dependem de infraestrutura e credenciais reais.

## Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Bean Validation
- Oracle Database XE
- Flyway
- Maven
- Docker e Docker Compose
- GitHub Actions
- GitHub Container Registry

## Desafios encontrados e soluções

- **Configuração de ambiente:** parâmetros de conexão foram externalizados por variáveis de ambiente para permitir diferentes ambientes.
- **Reprodutibilidade:** Dockerfile multi-stage padroniza compilação e execução.
- **Persistência:** volume nomeado do Compose mantém os dados do Oracle entre reinicializações.
- **Automação:** pipeline separa build/testes, publicação da imagem e deploys condicionais por ambiente.
- **Deploy:** exige configuração de servidores, chaves SSH, secrets e aprovação de produção antes de afirmar que staging/produção estão operacionais.

## Checklist obrigatório da entrega

- [x] Projeto compactado em ZIP com estrutura organizada
- [x] Dockerfile criado
- [x] docker-compose.yml criado
- [x] Pipeline com build e execução de testes configurados
- [x] README com instruções e seção de evidências
- [ ] Deploy real realizado em staging
- [ ] Deploy real realizado em produção
- [ ] Prints reais de build, testes e deploy adicionados
- [ ] Documentação técnica em PDF/PPT anexada

**Atenção:** marque os itens pendentes como concluídos somente depois de executar e validar cada etapa.
