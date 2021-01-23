### Practice Test - Bliss

## :arrow_forward: Como executar 

 1. Gerar [personal access token](https://developer.github.com/v3/auth/#via-oauth-and-personal-access-tokens) com acesso a leitura de repositórios e usuários.
 2.  Substituir o valor em  **api_token** no arquivo **gradle.properties** pelo token gerado;
 3. sincronizar o gradle (*sync now*);
 4. Executar projeto a partir da branch master;
 5. A aplicação necessita de internet para funcionar;


## :computer: Tecnologias utilizadas

**Bibliotecas**
 - Koin;
 - Coroutines; 
 - Room Database
 - Picasso, Retrofit e Gson;
 - MocK; 

**Linguagem**: Kotlin

**Arquitetura:** MVVM

## :scroll: Features 

 - [x] Listar emojis do github; 
 - [x] Salvar esses emojis no banco de dados local;
 - [x] Selecionar emoji de forma aleatoria;
 - [x] Consultar repositorio atraves do nome de usuario;
 - [x] Salvar eles no banco de dados local;
 - [x] Listar Avatar dos repositorios pesquisados
 - [x] Listar os repositorios do google: 

## :bar_chart: Justificativa para o uso das tecnologias 

**Decisões técnicas:**
Alguns fatores chave nortearam a maioria das decisões técnicas feitas no desenvolvimento dessa aplicação: código reutilizável, testável e facilmente substituível sem grandes refatorações a aplicação. Mesmo sendo uma aplicação pequena, as decisões foram tomadas visualizando um desenvolvimento contínuo e escalável.

**MVVM e LiveData**
 - Dentre as opções apresentadas para linguagem, optei pelo Kotlin por ter maior familiaridade com a linguagem.
 - A arquitetura MVVM foi escolhida devido a sua testabilidade, separação de responsabilidade e fácil integração com os componentes do Jetpack e outros recursos utilizados no projeto como Coroutines e injeção de dependência. Utilizar o MVVM com repository também facilitou a implementação desses processos e agregou mais uma camada de isolamento entre o viewModel e o datasource, facilitando sua substituição em caso de testes ou até na troca por outra API sem a necessidade de grande refatoração no código. 
 - O LiveData foi escolhido para o binding pela sua integração a arquitetura, familiaridade e respeito ao ciclo de vida.
 
 ** Room Database **
 - Facilidade de implementação e ser um biblioteca do jetpack compose e inclusive é mantida pela google
 
 **Injeção de dependência e coroutines**
 
 - Além de ser uma prática que auxilia na criação de uma boa arquitetura, a injeção de dependência apresenta as vantagens que nortearam a maioria das decisões técnicas feitas no desenvolvimento dessa aplicação: código reutilizável, testável e de facil refatoração.
 - O corutines foi escolhido para melhorar a performance e trabalhar com operações assíncronas com maior facilidade, além de ser integrada com ViewModel, facilitando ainda mais o processo. A biblioteca retrofit também possui suporte pra operações assíncronas  com coroutines na versão utilizada nessa aplicação.
 
**Escolha das bibliotecas de terceiros:**

- As bibliotecas da Square (retrofit, Gson e picasso) são open-source e possuem  confiabilidade dentro da comunidade Android devido as suas constantes atualizações, inovações e usos em diversas aplicações comerciais (algumas sendo utilizadas pela própria google como referência para suprir demandas não nativas). 
- Já o MockK e Koin foram escolhidos pelas otimizações focadas em Kotlin, documentação acessível e curva de aprendizado reduzida em comparação ao Dagger e Mockito (escolhas mais tradicionais para DI e mock). 
- Esses mesmos fatores também se aplicam para a escolha do Coroutines (que é desenvolvida pela Jetbrains mas ainda precisa ser importada ao projeto).


## :interrobang: Problemáticas encontradas 

 - Ao fazer um commit com um personal token, ele é automaticamente desativado pelo github, necessitando assim da configuração inicial colocada nessa documentação.
 - Ao utilizar a API sem autenticação, as chamadas são bloqueadas após 60 requests por hora ( a autenticação aumenta esse limite para 5000). 

## :thought_balloon: Será que esqueci alguma coisa? 
Caso tenha alguma dúvida, sugestão ou melhoria é só falar! (ou abrir um PR).
