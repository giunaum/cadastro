Webservice Rest Cadastro de Clientes
=
Webservice Rest de cadastro de clientes com geolocalização, incluído o nome, a idade, a data da requisição, a temperatura mínimima e máxima do dia da requisão, pegando o local mais próximo de onde foi feito a requisição.

Serviços
=

[url]/cliente/listar
-
Método GET que recupera toda a lista de clientes cadastrados no sitema.

[url]/cliente/obter/{id}
-
Método GET que recupera o cliente fornecendo o ID como parâmetro por URI.

[url]/cliente/salvar/{nome}/{idade}
-
Método GET que salva o cliente no sistema fornecendo nome e idade como parâmetros por URI. Junto com retorno deste serviço, vai o IP de quem requisitou o serviço para poder pegar localização e armazenar no sistema vinculando ao cliente cadastrado.

[url]/cliente/atualizar/{nome}/{idade}/{id}
-
Método GET que atualiza as informaçãos de nome e idade que são fornecidos como parâmetros por URI. Também é fornecido o ID como parâmetro para localizar o cliente a ser alterado.

[url]/cliente/excluir/{id}
-
Método GET que exclui um cliente conforme o ID fornecido como parâmetro por URI.

Ferramentas
=

Desenvolvimento
-
Para o desenvolvimento foi utilizado apenas a ide Intellij IDEA da Jetbrains como ferramenta. A escolha desta ferramenta foi feita devido a agilidade que ele proporciona devido ao seu auto-complete ser extremamente eficiente e intuitivo, facilitando muito o desenvolvimento back-end.

Teste
-
Para o teste foi utilizado o Postman por ser uma ferramenta apropriada para o consumo de webservices, armazendo um histórico de endereços utilizados, podendo ser separados por abas e por apresentar o retorno do objeto JSON com indentação bem formatada, ficando bem legível.
Devido ao consumo do webservice intranet não pegar o IP externo da máquia que está requisitando o serviço, foi feito um deploy no site da Heroku (https://dashboard.heroku.com/) para acessar o serviço externamente, pegando o IP externo para poder pegar o a localização do requisitante e fazer os testes do funcionamento real da aplicação.

Infraestrutura e Instruções
=

Banco de Dados
-
O SGBD (Sistema de Gerenciamento de Banco de Dados) utilizado neste projeto é o Postgres. É preciso instalar o Postgres no servidor que vai ser rodado a aplicação e criar o banco de dados chamado 'cadastro' ou outro de acordo com a configuração do arquivo de configuração 'application.properties' que fica dentro de 'resources'. As tabelas serão criadas automáticamentes ao rodar a aplicação.

Gerar a Aplicação
-
Para gerar a aplicação é preciso compilar o projeto pelo maven e pegar o arquivo jar. Para compilar, utilize o comando 'mvn install' no terminal ou execultar o comando install diretamente na ide. Mas caso seje necessário, altere antes as configurações do banco de dados no arquivo 'application.properties'.

Executar a Aplicação
-
Para rodar a aplicação em um ambiente de homologação ou produção, execute o seguinte comando no terminal 'java -jar cadastro-1.0-SNAPSHOT.jar'.
Para acessar, utilize o host e porta na url e o serviço desejado como descrito em 'Serviços'.
Uma outra alternativa é utilizar Jenkins e configurar para rodar ao ser feito o deploy do projeto.

Teste
-
Como citado nas ferramentas usadas no teste durante o desenvolvimento, pode ser utilizado a aplicação Postman e o site Heroku para testar o webservice.
Para teste no Heroku com serviço já disponiblizado no desenvolvimento, segue o link: https://ws-cadastro.herokuapp.com + o endereço do serviço.