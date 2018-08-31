Webservice Rest Cadastro de Clientes
================================================
Webservice Rest de cadastro de clientes com geolocalização, incluído o nome, a idade, a data da requisição, a temperatura
mínimima e máxima do dia da requisão, pegando o local mais próximo de onde foi feito a requisição.

Serviços
================================================

<url>/cliente/listar
--------------------------------------------------------------------------------------------------
Método GET que recupera toda a lista de clientes cadastrados no sitema.

<url>/cliente/obter/{id}
--------------------------------------------------------------------------------------------------
Método GET que recupera o cliente fornecendo o ID como parâmetro por URI.

<url>/cliente/salvar/{nome}/{idade}
--------------------------------------------------------------------------------------------------
Método GET que salva o cliente no sistema fornecendo nome e idade como parâmetros por URI.
Junto com retorno deste serviço, vai o IP de quem requisitou o serviço para poder pegar localização e armazenar no sistema
vinculando ao cliente cadastrado.

<url>/cliente/atualizar/{nome}/{idade}/{id}
--------------------------------------------------------------------------------------------------
Método GET que atualiza as informaçãos de nome e idade que são fornecidos como parâmetros pro URI. Também é forne-
cido o ID como parâmetro para localizar o cliente a ser alterado.

<url>/cliente/excluir/{id}
--------------------------------------------------------------------------------------------------
Método GET que exclui um cliente conforme o ID fornecido como parâmetro por URI.

Ferramentas
================================================

Desenvolvimento
--------------------------------------------------------------------------------------------------
Para o desenvolvimento foi utilizado apenas a ide Intellij IDEA da Jetbrains como ferramenta. A escolha desta ferramenta foi
feita devido a agilidade que ele proporciona devido ao seu auto-complete ser extremamente eficiente e intuitivo, facilitando
muito o desenvolvimento back-end.

Teste
--------------------------------------------------------------------------------------------------
Para o teste foi utilizado o Postman por ser uma ferramenta apropriada para o consumo de webservices, armazendo um his-
tórico de endereços utilizados, podendo ser separados por abas e por apresentar o retorno do objeto JSON com indentação
bem formatada, ficando bem legível.
Devido ao consumo do webservice intranet não pegar o IP externo da máquia que está requisitando o serviço, foi feito um
deploy no site da Heroku (https://dashboard.heroku.com/) para acessar o serviço externamente, pegando o IP externo para
poder pegar o a localização do requisitante e fazer os testes do funcionamento real da aplicação.

Infraestrutura e Instruções
================================================

Postgres
--------------------------------------------------------------------------------------------------
É preciso ter instalado o Postgres no servidor que vai ser rodado a aplicação e criar o banco chamado 'cadastro'. As tabelas
serão criadas automáticamentes ao rodar a aplicação.

Maven
--------------------------------------------------------------------------------------------------
Para compilar o projeto e pegar o arquivo jar é necessário o usar o maven, utilizando o comando 'mvn install' no terminal ou
execultar o comando diretamente na ide. Mas caso seje necessário, altere antes as configurações do banco de dados no
arquivo  chamado 'application.properties' que fica dentro de 'resources'.

Postman e Heroku
--------------------------------------------------------------------------------------------------
Como citado nas ferramentas usadas no teste durante o desenvolvimento, pode ser utilizado a aplicação Postman e o site
Heroku para testar o webservice.

Arquivo JAR
--------------------------------------------------------------------------------------------------
Para rodar a aplicação em ambiente de produção, execute o comando 'java -jar cadastro-1.0-SNAPSHOT.jar' no terminal.
Para acessar, utilize o host e porta na url e o serviço desejado como descrito em 'Serviços'.

Jenkins
--------------------------------------------------------------------------------------------------
Uma alternativa seria configurar o Jenkins para rodar o serviço no deploy.
