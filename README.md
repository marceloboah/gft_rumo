# Instructions to run this application

*** Make sure JDK 1.8 is installed and configured ***
Link for download --> https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html

Esta é uma aplicação que le uma massa de dados de arquivos no formato Json, armazena em um banco de dados em memória e expõe um endpoint para consultar esta informação.

Esta aplicação está disponível no git:
https://github.com/marceloboah/gft_rumo

1)Para executar a aplicação basta
abrir o prompt de comando e acessar dentro da pasta target do projeto a aplicação gft-api em arquivo  jar.
\crud-spring-boot-modified\target\gft-api-1.0.1-RELEASE.jar

Atenção para mudanças no nome do arquivo referente a alterções na versão.

Para encerrar a aplicação efetue ctrl+c no prompt em execução.


2)Antes de iniciar a execução do arquivo crie uma pasta data na raiz de C:
C:\data
Em seguida coloque os arquivos de leitura json dentro da pasta.

3)Esta aplicação funciona com um banco de dados em memória do tipo H2.
Foi escolhida a opção com escrita em arquivo.
Crie também uma pasta h2 dentro da pasta data para o bom funcionamento do BD em memória.
O banco de dados pode ser acessado pelo link:

http://localhost:9099/h2

Para acessar utilize
JDBC URL:   jdbc:h2:file:C:/data/h2/mh2
User Name:  sa
Password:

4) O menu da aplicação pode ser acessível por:
http://localhost:9099

5) Para executar a carga dos arquivos acesse:
http://localhost:9099/api/import/validation

6) Para visualizar os dados utilize o console do H2 ou acesse o endpoint:
http://localhost:9099/api/product

---------------------------------------

Considerações:

Tecnologias: Java 8 / Sprint Boot / Maven / Git / JUnit / Mockito. 

Explorar os novos recursos do Java 8 (lambdas, streams, etc) : Na classe método readFiles() na linha 100 é utilizado um filter lambda. Eu uso o lambda mais para filter. A experiência mostrou que para loops o lambda nem sempre é eficiente por causa do escopos das variáves que são criadas externamente e as vezes internamente não são acessíveis ou vice versa.


Explorar recursos do Spring Framework : São utilizadas as interfaces em repository para acesso a banco.

Organização e estrutura do projeto: Divisão de pacotes e MVC.

Uso do git: Item enviado para repositório.
https://github.com/marceloboah/gft_rumo

Os Teste Unitários estão nas classes ApplicationTest que é executado durante o mnv build e na classe ProductTest que não é executado na subida do servidor por estar fora do local padrão, mas pode ser executado via JUnit. Foram criados testes não persistentes na camada Controller e BusinessObject.
Geralmente se usa um teste unitário para cada endpoint para garantir que todos os serviçoes rest subiram no start da aplicação. Execuções com persistencia via get e post não foram configuradas.

O projeto está disponivel  em https://github.com/marceloboah/gft_rumo

O arquivo README.md contém instruções para compilar e rodar o projeto;

É possível rodar o projeto utilizando as instruções no README.md;


Design e funcionamento da solução: Quando se entrega uma solução ao cliente sempre é observado se o item agrega valor. O valor para o cliente é sempre o mais importante da aplicação.

Espera-se ser satisfatória:
-Qualidade do código
-Qualidade dos testes unitários
-Uso dos recursos da linguagem e do framework
-Criatividade, tamanho e complexidade da solução
-Uso de boas práticas e convenções de implementação

Versionamento e controle do repositório: O versionamento será alterado conforme atualização no repositório tendo em vista releases, versões e correções.

Possibilidade para evolução da aplicação:
A aplicação permite muita evolução como use de lombok, diretivas, construção futura de gulp ou grunt, sistemas de rotas, adição de framework no frontend.

Parte 1: Ler e armazenar:
Item realizado no endpoint http://localhost:9099/api/import/validation

Foi utilizado o método save do SpringBoot que verifica se o item já existe no BD e caso exista ele apenas atualiza na solução porém o código não relizou a validação durante os testes.
Foi criada uma nova solução com consulta do objeto, porém o tempo de processamento aumentou com relação ao endpoint anterior. 
Foi utilizado o paralelismo através de abertura de uma Thread para cada arquivo conforme solicitado.


Parte 2: Consultar dados:
Este item fica acessível no endpoint através de busca. 
(Solução 1) Via tela de menu (Regras no Javascript)
http://localhost:9099/api/api/products/find?name={nomeProduto}&floor={valorMinimo}&valmax={valorMaximo}&paginaatual={numeroPagina}

Para este item a visualização é recomendável via tela de menu.

Exemplos
http://localhost:9099/api/products/find?name=RTIX&floor=3&valmax=4&paginaatual=1

http://localhost:9099/api/products/find?name="RTIX"&floor=3&valmax=4&paginaatual=1

http://localhost:9099/api/products/find?name=null&floor=3&valmax=4&paginaatual=1

É obrigatório o campo página atual

(Solução 2) Via endpoint REST (Endpoint não possui regras de combinação)
Foi realizada uma redundancia da solucação para melhor visuallização via endpoint.
http://localhost:9099/api/api/products/search?name={nomeProduto}&floor={valorMinimo}&valmax={valorMaximo}&paginaatual={numeroPagina}

Exemplos
http://localhost:9099/api/products/search?name=RTIX&floor=3&valmax=6&paginaatual=1

http://localhost:9099/api/products/search?name=null&floor=3&valmax=6&paginaatual=2

Também será possível acessar o menu Find Products.

A visualização dos dados também é possível através do endpoint 
http://localhost:9099/api/products/all

Também é possível acessar o menu Lista All Products.

Parte 3: Reimportar dados:
Este item está disponível em
http://localhost:9099/api/clean/import

OBS: Falta paginação.


--------------------------------
Alterações

1.0.1-RELEASE
A solução no endpoint http://localhost:9099/api/initreader foi alterada e foi corrigido um bug de iteração a cada duas linhas. No momento dos testes foi verificado que a coluna industry não consta como chave e neste caso o método save não soluciona o problema.

Foi criado um novo endpoint http://localhost:9099/api/import/validation com validação de busca no banco que passa a ser a solução principal. O código anterior se mantém no programa para fins de comparação.


1.0.2-RELEASE
Realizada busca.

1.0.3-RELEASE
Realizada paginação.


