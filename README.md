# Trabalho-SD

Projeto elaboradorado por Diego Wallace Santos e Raphael de Almeida Torlay para a disciplina de Sistemas Distribuídos do 5º ciclo de Análise e Desenvolvimento de Sistemas, Fatec Carapicuíba, professor Evandro Luquini.

A proposta do projeto era elaborar um sistema P2P para processar expressões em históricos da bolsa de valores, utilizando conceitos estudados em sala de aula, como TCP, UDP e Sockets em Java.

Como testar o projeto:

O projeto funciona em dois módulos: Mestre e Escravo. O Mestre, quando é iniciado, procura e armazena os endereços dos Escravos na mesma rede e os atualiza quanto a base de arquivos de históricos da bolsa de valores. Por este motivo, é importante que os Escravos já estejam em execução quando o Mestre for executado. 

Para atualizar a base de arquivos, basta acessar o Yahoo! Finance e baixar o histórico de uma empresa, com a extensão .csv e salvar em BolsaMestre/arquivos.

Após a transferência dos arquivos, os Escravos ficam aguardando uma expressão no formato (exemplo) Pn_OPEN > P(n+1)_CLOSE, onde n = 1, 2, ..., 5, podendo utilizar também outros dados como o valor máximo (Pn_MAX) ou o valor mínimo (Pn_MIN) de um dia. n = 1 representa o dia atual, n = 2 representa o dia anterior a n = 1, e assim por diante. Os testes são realizados de 5 em 5 dias, cronologicamente, ou seja, o arquivo .csv é lido da última linha para a primeira.

A complexidade da expressão pode afetar o desempenho do escravo.

A distribuição de expressões para Escravos é rotativa. Se uma mensagem for enviada para um escravo enquanto ele estiver processando, a próxima expressão será processada assim que ele acabar, mas não foi testado o envio de mais de uma mensagem enquanto um escravo está processando.

Não foi testado um limite de escravos rodando ao mesmo tempo. Nossos testes chegaram a utilizar 1 Mestre e 3 Escravos em uma rede local.
	
O Mestre realiza um broadcast em todas as redes locais que o computador estiver conectado. Você pode perceber isso caso tenha a ferramenta Hamachi ou VMWare instalada em seu computador.

A resposta do Escravo é no formato "0.csv: x vezes encontradas, y acertos. n.csv: ... .. Fim do processamento." A ordem dos arquivos é a ordem que eles foram recebidos, ou seja, na ordem alfabética que eles se encontram em BolsaMestre/arquivos. "x vezes encontradas" representa a quantidade de vezes que a expressão deu certo e, "y acertos" representa a quantidade de vezes, em cima de x, que a simulação de compra retornou lucro para o comprador.

O teste de compras é realizado comprando 3 ações no dia atual e vendendo-as nos próximos 3 dias.

Para fins de teste, é possível executar o Mestre e o Escravo na mesma máquina, no entanto não é possível executar mais de um Mestre ou mais de um Escravo na mesma máquina, por problemas de conflitos nas portas utilizadas para a comunicação.

Ao baixar o projeto, o histórico de ações da PETR4 estará na pasta de arquivos do Mestre, com o nome padrão do Yahoo! Finance, table.csv.
